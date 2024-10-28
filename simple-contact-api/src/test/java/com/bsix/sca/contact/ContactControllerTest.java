package com.bsix.sca.contact;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
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

@WebMvcTest
@Import(value = {ContactModelAssembler.class})
public class ContactControllerTest {

  @Autowired private MockMvc mvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private ContactRepository repository;

  private static ResultMatcher matchPagedContactModel() {
    return result -> {
      jsonPath("_embedded").isMap().match(result);
      jsonPath("_embedded.contactList").isArray().match(result);

      jsonPath("_links").isMap().match(result);
      jsonPath("_links.self").isMap().match(result);
      jsonPath("_links.self.href").isString().match(result);

      jsonPath("page").isMap().match(result);
      jsonPath("page.size").isNumber().match(result);
      jsonPath("page.totalElements").isNumber().match(result);
      jsonPath("page.totalPages").isNumber().match(result);
      jsonPath("page.number").isNumber().match(result);
    };
  }

  private ResultMatcher matchContactModel() {
    return result -> {
      jsonPath("id").isString().match(result);
      jsonPath("firstName").exists();
      jsonPath("lastName").exists();
      jsonPath("phoneNumber").exists();
      jsonPath("profilePicture").exists();
      jsonPath("address").exists();

      jsonPath("_links").isMap().match(result);
      jsonPath("_links.self").isMap().match(result);
      jsonPath("_links.self.href").isString().match(result);
      jsonPath("_links.collection").isMap().match(result);
      jsonPath("_links.collection.href").isString().match(result);
    };
  }

  private ResultMatcher matchContactNotFoundProblemDetail(String id) {
    return result -> {
      jsonPath("type").value("about:blank").match(result);
      jsonPath("title").value("Contact Not Found").match(result);
      jsonPath("status").value(404).match(result);
      jsonPath("detail").value("Unable to locate contact with id: " + id);
      jsonPath("instance").value("/api/contacts/" + id);
    };
  }

  private Contact contact;

  @BeforeEach
  void init() {
    contact =
        new Contact(
            "1",
            "Andrew",
            "Young",
            "234-567-8904",
            "profile32.jpg",
            new Contact.Address("505 Spruce St", "Sample City", "TX", "23459", "USA"));
  }

  @Test
  void whenGetContacts_returnPagedContactModel() throws Exception {
    Page<Contact> page = new PageImpl<>(List.of(contact));

    when(repository.findAll(any(Pageable.class))).thenReturn(page);

    mvc.perform(get("/api/contacts")).andExpect(matchPagedContactModel());

    verify(repository, times(1)).findAll(any(Pageable.class));
  }

  @Test
  void whenGetKnownContact_returnContactModel() throws Exception {
    when(repository.findById("known-id")).thenReturn(Optional.of(contact));

    mvc.perform(get("/api/contacts/known-id")).andExpect(matchContactModel());

    verify(repository).findById("known-id");
  }

  @Test
  void whenGetUnknownContact_returnProblemDetail() throws Exception {
    when(repository.findById("unknown-id")).thenThrow(new ContactNotFoundException("unknown-id"));

    mvc.perform(get("/api/contacts/" + "unknown-id"))
        .andExpect(matchContactNotFoundProblemDetail("unknown-id"));

    verify(repository, times(1)).findById("unknown-id");
  }

  @Test
  void whenPostContact_returnContactModel() throws Exception {
    when(repository.save(contact)).thenReturn(contact);

    mvc.perform(
            post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact)))
        .andExpect(status().is(201))
        .andExpect(matchContactModel());

    verify(repository, times(1)).save(contact);
  }

  @Test
  void whenPutKnownContact_returnContactModel() throws Exception {
    when(repository.findById(contact.getId())).thenReturn(Optional.of(contact));
    when(repository.save(any(Contact.class)))
        .thenReturn(
            new Contact(
                contact.getId(),
                "first",
                "last",
                "phone",
                "pfp",
                new Contact.Address("street", "city", "state", "zip", "country")));

    mvc.perform(
            put("/api/contacts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contact)))
        .andExpect(matchContactModel())
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(contact.getId()));

    verify(repository, times(1)).findById(any(String.class));
    verify(repository, times(1)).save(any(Contact.class));
  }

  @Test
  void whenPutUnknownContact_returnProblemDetail() throws Exception {
    when(repository.findById("unknown-id")).thenThrow(new ContactNotFoundException("unknown-id"));

    mvc.perform(
        put("/api/contacts/unknown-id")
            .content(objectMapper.writeValueAsString(contact))
            .contentType(MediaType.APPLICATION_JSON));

    verify(repository, times(1)).findById("unknown-id");
  }

  @Test
  void whenDeleteContact_return() throws Exception {
    doNothing().when(repository).deleteById("id");

    mvc.perform(delete("/api/contacts/id")).andExpect(status().is(204));

    verify(repository, times(1)).deleteById("id");
  }
}
