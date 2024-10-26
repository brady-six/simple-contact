package com.bsix.simplecontactapi.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

  private final ContactRepository contactRepository;

  public ContactService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public Page<Contact> getContacts(Pageable pageable) {
    return contactRepository.findAll(pageable);
  }

  public Contact getContact(String id) {
    return contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
  }

  public Contact postContact(Contact contact) {
    return contactRepository.save(contact);
  }

  public Contact putContact(String id, Contact contact) {
    Contact current = getContact(id);

    current.setFirstName(contact.getFirstName());

    current.setLastName(contact.getLastName());

    current.setPhoneNumber(contact.getPhoneNumber());

    Contact.Address address =
        new Contact.Address.AddressBuilder()
            .street(contact.getAddress().getStreet())
            .city(contact.getAddress().getCity())
            .state(contact.getAddress().getState())
            .zipcode(contact.getAddress().getZipcode())
            .country(contact.getAddress().getCountry())
            .build();

    current.setAddress(address);

    return contactRepository.save(current);
  }

  public void deleteContact(String id) {
    getContact(id); // Ensure this contact exists
    contactRepository.deleteById(id);
  }
}
