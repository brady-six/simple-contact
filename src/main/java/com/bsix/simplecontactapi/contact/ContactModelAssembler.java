package com.bsix.simplecontactapi.contact;

import io.micrometer.common.lang.NonNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContactModelAssembler
    implements RepresentationModelAssembler<Contact, EntityModel<Contact>> {

  @Override
  public @NonNull EntityModel<Contact> toModel(@NonNull Contact contact) {
    return EntityModel.of(
        contact,
        linkTo(methodOn(ContactController.class).getContact(contact.getId())).withSelfRel(),
        linkTo(methodOn(ContactController.class).getContacts()).withRel("contacts"));
  }

  @Override
  public @NonNull CollectionModel<EntityModel<Contact>> toCollectionModel(
      @NonNull Iterable<? extends Contact> contacts) {

    List<EntityModel<Contact>> models = new ArrayList<>();

    contacts.forEach(contact -> models.add(toModel(contact)));

    return CollectionModel.of(
        models, linkTo(methodOn(ContactController.class).getContacts()).withSelfRel());
  }
}
