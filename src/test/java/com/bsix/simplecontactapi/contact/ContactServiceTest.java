package com.bsix.simplecontactapi.contact;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ContactServiceTest {

  @Mock private ContactRepository contactRepository;

  @InjectMocks private ContactService contactService;

  private Contact contact;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    contact =
        new Contact(
            "1",
            "John",
            "Doe",
            "1234567890",
            new Contact.Address("123 Main St", "Springfield", "IL", "62701", "USA"),
            null);
  }

  @Test
  public void testGetContacts() {
    Pageable pageable = Pageable.unpaged();
    Page<Contact> pageMock = mock(Page.class);
    when(pageMock.getContent()).thenReturn(Collections.singletonList(contact));
    when(contactRepository.findAll(pageable)).thenReturn(pageMock);

    List<Contact> contacts = contactService.getContacts(pageable);

    assertEquals(1, contacts.size());
    assertEquals("John", contacts.getFirst().getFirstName());
    verify(contactRepository).findAll(pageable);
  }

  @Test
  public void testGetContact() {
    when(contactRepository.findById("1")).thenReturn(Optional.of(contact));

    Contact foundContact = contactService.getContact("1");

    assertEquals("John", foundContact.getFirstName());
    verify(contactRepository).findById("1");
  }

  @Test
  public void testGetContact_NotFound() {
    when(contactRepository.findById("1")).thenReturn(Optional.empty());

    Exception exception =
        assertThrows(ContactNotFoundException.class, () -> contactService.getContact("1"));

    assertEquals("Could not find contact with id 1", exception.getMessage());
  }

  @Test
  public void testPostContact() {
    when(contactRepository.save(contact)).thenReturn(contact);

    Contact savedContact = contactService.postContact(contact);

    assertEquals("John", savedContact.getFirstName());
    verify(contactRepository).save(contact);
  }

  @Test
  public void testPatchContact() {
    when(contactRepository.findById("1")).thenReturn(Optional.of(contact));
    when(contactRepository.save(contact)).thenReturn(contact);
    Contact updatedContact = new Contact(null, "Jane", null, null, null, null);

    Contact patchedContact = contactService.patchContact("1", updatedContact);

    assertEquals("Jane", patchedContact.getFirstName());
    assertEquals("Doe", patchedContact.getLastName());
    verify(contactRepository).save(contact);
  }

  @Test
  public void testPutContact() {
    when(contactRepository.findById("1")).thenReturn(Optional.of(contact));
    when(contactRepository.save(contact)).thenReturn(contact);
    Contact newContact =
        new Contact(
            null,
            "Jane",
            "Smith",
            "0987654321",
            new Contact.Address("456 Elm St", "Springfield", "IL", "62702", "USA"),
            null);

    Contact updatedContact = contactService.putContact("1", newContact);

    assertEquals("Jane", updatedContact.getFirstName());
    assertEquals("Smith", updatedContact.getLastName());
    verify(contactRepository).save(contact);
  }

  @Test
  public void testDeleteContact() {
    when(contactRepository.findById("1")).thenReturn(Optional.of(contact));

    contactService.deleteContact("1");

    verify(contactRepository).deleteById("1");
  }

  @Test
  public void testDeleteContact_NotFound() {
    when(contactRepository.findById("1")).thenReturn(Optional.empty());

    assertThrows(ContactNotFoundException.class, () -> contactService.deleteContact("1"));
  }
}
