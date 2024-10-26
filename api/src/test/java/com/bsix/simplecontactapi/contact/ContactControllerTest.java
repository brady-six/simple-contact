package com.bsix.simplecontactapi.contact;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(ContactController.class)
@Import(ContactModelAssembler.class)
public class ContactControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @MockBean private ContactService contactService;
  private Contact contact;

  @Autowired
  public ContactControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
  }

  private static ResultMatcher matchContactCollection() {
    return (result) -> {
      jsonPath("_embedded").isMap().match(result);
      jsonPath("_embedded.contactList").isArray().match(result);
      jsonPath("_links").isMap().match(result);
      jsonPath("_links.self.href").isString().match(result);
    };
  }

  private static ResultMatcher matchContactEntity(Contact contact) {
    return (result) -> {
      jsonPath("id").value(contact.getId()).match(result);
      jsonPath("firstName").value(contact.getFirstName()).match(result);
      jsonPath("lastName").value(contact.getLastName()).match(result);
      jsonPath("phoneNumber").value(contact.getPhoneNumber()).match(result);
      jsonPath("address").isMap().match(result);
      jsonPath("address.street").value(contact.getAddress().getStreet()).match(result);
      jsonPath("address.city").value(contact.getAddress().getCity()).match(result);
      jsonPath("address.state").value(contact.getAddress().getState()).match(result);
      jsonPath("address.zipcode").value(contact.getAddress().getZipcode()).match(result);
      jsonPath("address.country").value(contact.getAddress().getCountry()).match(result);
      jsonPath("profilePicture").value(contact.getProfilePicture()).match(result);
      jsonPath("_links").isMap().match(result);
      jsonPath("_links.self.href").isString().match(result);
      jsonPath("_links.contacts.href").isString().match(result);
    };
  }

  private static ResultMatcher matchContactNotFoundProblem(String badId) {

    return result -> {
      status().isNotFound().match(result);
      jsonPath("$.type").value("about:blank");
      jsonPath("$.title").value("Contact Not Found");
      jsonPath("$.status").value(404);
      jsonPath("$.detail").value("Could not find contact with id " + badId);
      jsonPath("$.instance").value("/api/contacts/" + badId);
    };
  }

  @BeforeEach
  void init() {
    contact =
        new Contact(
            "1",
            "John",
            "Doe",
            "1234567890",
            new Contact.Address("100 NW Lombok Lane", "Seattle", "WA", "09112", "USA"),
            "john.png");
  }

  @Test
  void testGetContacts() throws Exception {

    Page<Contact> contactPage = new PageImpl<>(Collections.singletonList(contact));

    when(contactService.getContacts(any(Pageable.class))).thenReturn(contactPage);

    mockMvc
        .perform(get("/api/contacts"))
        .andExpect(status().isOk())
        .andExpect(matchContactCollection());

    verify(contactService, times(1)).getContacts(any());
  }

  @Test
  void testPutKnownContact() throws Exception {
    when(contactService.putContact(any(String.class), any(Contact.class))).thenReturn(contact);

    mockMvc
        .perform(
            put("/api/contacts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact)))
        .andExpect(status().isOk())
        .andExpect(matchContactEntity(contact));

    verify(contactService, times(1)).putContact(any(), any());
  }

  @Test
  void testPutUnknownContact() throws Exception {
    when(contactService.putContact("1", contact)).thenThrow(new ContactNotFoundException("1"));

    mockMvc
        .perform(
            put("/api/contacts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact)))
        .andExpect(status().is(404))
        .andExpect(matchContactNotFoundProblem("1"));

    verify(contactService, times(1)).putContact(any(), any());
  }

  @Test
  void testDeleteKnownContact() throws Exception {
    doNothing().when(contactService).deleteContact("1");

    mockMvc.perform(delete("/api/contacts/1")).andExpect(status().isNoContent());

    verify(contactService, times(1)).deleteContact("1");
  }

  @Test
  void testDeleteUnknownContact() throws Exception {
    doThrow(new ContactNotFoundException("1")).when(contactService).deleteContact("1");

    mockMvc.perform(delete("/api/contacts/1")).andExpect(matchContactNotFoundProblem("1"));

    verify(contactService, times(1)).deleteContact("1");
  }
}
