package com.bsix.simplecontactapi.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public List<Contact> getContacts(Pageable pageable) {
        Page<Contact> page = contactRepository.findAll(pageable);
        return page.getContent();
    }

    public Contact getContact(String id) {
        return contactRepository.findById(id).orElseThrow();
    }

    public Contact patchContact(Contact contact) {
        Contact current = getContact(contact.getId());

        String newFirstName = contact.getFirstName();
        String newLastName = contact.getLastName();
        String newPhoneNumber = contact.getPhoneNumber();
        Contact.Address newAddress = contact.getAddress();
        String newProfilePicture = contact.getProfilePicture();

        if (newFirstName != null) current.setFirstName(newFirstName);

        if (newLastName != null) current.setLastName(newFirstName);

        if (newPhoneNumber != null) current.setPhoneNumber(newPhoneNumber);

        if (newAddress != null) {

            String newStreet = newAddress.getStreet();
            String newCity = newAddress.getCity();
            String newState = newAddress.getState();
            String newZipcode = newAddress.getZipcode();
            String newCountry = newAddress.getCountry();

            if (newStreet != null) current.getAddress().setStreet(newStreet);

            if (newCity != null) current.getAddress().setCity(newCity);

            if (newState != null) current.getAddress().setState(newState);

            if (newZipcode != null) current.getAddress().setZipcode(newZipcode);

            if (newCountry != null) current.getAddress().setCountry(newCountry);

        }

        if (newProfilePicture != null) current.setProfilePicture(newProfilePicture);

        return contactRepository.save(current);

    }

    public Contact putContact(Contact contact) {
        Contact current = getContact(contact.getId());

        current.setFirstName(contact.getFirstName());

        current.setLastName(contact.getLastName());

        current.setPhoneNumber(contact.getPhoneNumber());

        Contact.Address address = new Contact.Address.AddressBuilder()
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
