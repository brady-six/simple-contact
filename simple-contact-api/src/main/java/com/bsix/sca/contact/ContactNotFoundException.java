package com.bsix.sca.contact;

public class ContactNotFoundException extends RuntimeException {
  public ContactNotFoundException(String id) {
    super("Unable to locate contact with id: " + id);
  }
}
