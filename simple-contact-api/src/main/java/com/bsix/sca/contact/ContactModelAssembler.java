package com.bsix.sca.contact;

import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ContactModelAssembler
    implements RepresentationModelAssembler<Contact, EntityModel<Contact>> {

  @Override
  public @NonNull EntityModel<Contact> toModel(@NonNull Contact contact) {
    var self = Link.of("/api/contacts/" + contact.getId(), IanaLinkRelations.SELF);
    var coll = Link.of("/api/contacts", IanaLinkRelations.COLLECTION);

    return EntityModel.of(contact, self, coll);
  }
}
