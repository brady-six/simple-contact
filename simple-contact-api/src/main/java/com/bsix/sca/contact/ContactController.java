package com.bsix.sca.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/contacts")
public class ContactController {

  @Autowired private ContactRepository contactRepository;

  @Autowired private ContactModelAssembler contactModelAssembler;

  @GetMapping
  PagedModel<EntityModel<Contact>> getContacts(@PageableDefault Pageable pageable) {
    var page = contactRepository.findAll(pageable);

    var contactModels = page.stream().map(contactModelAssembler::toModel).toList();

    var pageModel =
        PagedModel.of(
            contactModels,
            new PagedModel.PageMetadata(
                page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()));

    pageModel.add(
        Link.of(
            "/api/contacts?page=" + page.getNumber() + "&size=" + page.getSize(),
            IanaLinkRelations.SELF));

    if (page.hasPrevious())
      pageModel.add(
          Link.of(
              "/api/contacts?page=" + (page.getNumber() - 1) + "&size=" + page.getSize(),
              IanaLinkRelations.PREV));
    if (page.hasNext())
      pageModel.add(
          Link.of(
              "/api/contacts?page=" + (page.getNumber() + 1) + "&size=" + page.getSize(),
              IanaLinkRelations.NEXT));

    return pageModel;
  }

  @GetMapping(path = "/{id}")
  EntityModel<Contact> getContact(@PathVariable String id) {
    var contact = contactRepository.findById(id).orElseThrow();
    return contactModelAssembler.toModel(contact);
  }
}
