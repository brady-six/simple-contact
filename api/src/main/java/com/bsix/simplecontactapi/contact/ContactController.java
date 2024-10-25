package com.bsix.simplecontactapi.contact;

import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/contacts")
public class ContactController {

  private final ContactService contactService;

  private final ContactModelAssembler modelAssembler;

  public ContactController(ContactService contactService, ContactModelAssembler modelAssembler) {
    this.contactService = contactService;
    this.modelAssembler = modelAssembler;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<Contact>>> getContacts(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

    if (page < 0) throw new IllegalArgumentException("Page number must not be negative");

    if (size <= 0) throw new IllegalArgumentException("Size must be greater than zero.");

    return ResponseEntity.ok(
        modelAssembler.toCollectionModel(contactService.getContacts(PageRequest.of(page, size))));
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<Contact>> getContact(@PathVariable String id) {
    return ResponseEntity.ok(modelAssembler.toModel(contactService.getContact(id)));
  }

  @PostMapping
  public ResponseEntity<EntityModel<Contact>> postContact(@RequestBody Contact contact) {
    Contact savedContact = contactService.postContact(contact);
    return ResponseEntity.created(
            linkTo(methodOn(ContactController.class).getContact(savedContact.getId())).toUri())
        .body(modelAssembler.toModel(savedContact));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EntityModel<Contact>> putContact(
      @PathVariable String id, @RequestBody Contact contact) {
    return ResponseEntity.ok(modelAssembler.toModel(contactService.putContact(id, contact)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContact(@PathVariable String id) {
    contactService.deleteContact(id);
    return ResponseEntity.noContent().build();
  }
}
