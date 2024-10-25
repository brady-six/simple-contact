package com.bsix.simplecontactapi.contact;

public class ContactNotFoundException extends RuntimeException {
  public ContactNotFoundException(String id) {
    super("Could not find contact with id " + id);
  }
}
