package com.bsix.simplecontactapi;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.bsix.simplecontactapi.contact.Contact;
import com.bsix.simplecontactapi.contact.ContactRepository;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.*;

@SpringBootTest(
    classes = {SeedTest.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppTest {

  @Autowired private TestRestTemplate template;

  @Autowired private ContactRepository repo;

  @Test
  void contextLoads() {
    assert template != null;
    assert repo != null;
  }

  @Test
  void testGetContacts() throws URISyntaxException {
    final var req = new RequestEntity<>(HttpMethod.GET, new URI("/api/contacts"));

    final var ref = new ParameterizedTypeReference<CollectionModel<EntityModel<Contact>>>() {};

    final var res = template.exchange(req, ref);

    assert res.getBody() != null;

    assertThat(res.getStatusCode().value()).isEqualTo(200);
    assertThat(res.getBody().getContent().size()).isEqualTo(10);
  }

  @Test
  void testGetContactsIllegalPage() throws URISyntaxException {
    final var req = new RequestEntity<>(HttpMethod.GET, new URI("/api/contacts?page=-1"));

    final var ref = new ParameterizedTypeReference<EntityModel<ProblemDetail>>() {};

    final var res = template.exchange(req, ref);

    assert res.getBody() != null;
    assert res.getBody().getContent() != null;

    String actualTitle = res.getBody().getContent().getTitle();
    String actualDetail = res.getBody().getContent().getDetail();

    String expectedTitle = "Illegal Request Parameter";
    String expectedDetail = "Page number must not be negative";

    assertThat(res.getStatusCode().value()).isEqualTo(400);
    assertThat(actualTitle).isEqualTo(expectedTitle);
    assertThat(actualDetail).isEqualTo(expectedDetail);
  }

  @Test
  void testGetContactsIllegalSize() throws URISyntaxException {
    final var req = new RequestEntity<>(HttpMethod.GET, new URI("/api/contacts?size=0"));

    final var ref = new ParameterizedTypeReference<EntityModel<ProblemDetail>>() {};

    final var res = template.exchange(req, ref);

    assert res.getBody() != null;
    assert res.getBody().getContent() != null;

    String actualTitle = res.getBody().getContent().getTitle();
    String actualDetail = res.getBody().getContent().getDetail();

    String expectedTitle = "Illegal Request Parameter";
    String expectedDetail = "Size must be greater than zero.";

    assertThat(res.getStatusCode().value()).isEqualTo(400);
    assertThat(actualTitle).isEqualTo(expectedTitle);
    assertThat(actualDetail).isEqualTo(expectedDetail);
  }

  @Test
  void testGetKnownContact() throws URISyntaxException {
    Contact contact = repo.findAll().getFirst();
    String id = contact.getId();

    final var req = new RequestEntity<>(HttpMethod.GET, new URI("/api/contacts/" + id));

    final var ref = new ParameterizedTypeReference<EntityModel<Contact>>() {};

    final var res = template.exchange(req, ref);

    assertThat(res.getStatusCode().value()).isEqualTo(200);
  }

  @Test
  void testGetUnknownContact() throws URISyntaxException {
    String id = "unknown";

    final var req = new RequestEntity<>(HttpMethod.GET, new URI("/api/contacts/" + id));

    final var ref = new ParameterizedTypeReference<EntityModel<ProblemDetail>>() {};

    final var res = template.exchange(req, ref);

    assert res.getBody() != null;
    assert res.getBody().getContent() != null;

    String actualTitle = res.getBody().getContent().getTitle();
    String actualDetail = res.getBody().getContent().getDetail();

    String expectedTitle = "Contact Not Found";
    String expectedDetail = "Could not find contact with id unknown";

    assertThat(res.getStatusCode().value()).isEqualTo(404);
    assertThat(actualTitle).isEqualTo(expectedTitle);
    assertThat(actualDetail).isEqualTo(expectedDetail);
  }

  @Test
  void testPostContact() throws URISyntaxException {

    Contact contact =
        Contact.builder()
            .firstName("Tony")
            .lastName("Stark")
            .phoneNumber("121212121")
            .address(
                Contact.Address.builder()
                    .street("100 Main Street")
                    .city("New York City")
                    .state("New York")
                    .zipcode("12445")
                    .country("USA")
                    .build())
            .profilePicture("tony.png")
            .build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final var req = RequestEntity.post(new URI("/api/contacts")).headers(headers).body(contact);

    final var ref = new ParameterizedTypeReference<EntityModel<Contact>>() {};

    int prevCount = repo.findAll().size();

    final var res = template.exchange(req, ref);

    int currCount = repo.findAll().size();

    assertThat(res.getStatusCode().value()).isEqualTo(201);
    assertThat(prevCount + 1).isEqualTo(currCount);
  }

  @Test
  void testPutContact() {

    Contact before = repo.findAll().getFirst();

    String id = before.getId();

    Contact updatedContact =
        Contact.builder()
            .firstName("Amelia")
            .lastName("Rodriguez")
            .phoneNumber("6623395381")
            .address(
                Contact.Address.builder()
                    .street("152 Big Street")
                    .city("Big City")
                    .state("Big State")
                    .country("BSA")
                    .zipcode("99999")
                    .build())
            .profilePicture("amelia.png")
            .build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final var req = RequestEntity.put("/api/contacts/" + id).headers(headers).body(updatedContact);

    final var ref = new ParameterizedTypeReference<EntityModel<Contact>>() {};

    final var res = template.exchange(req, ref);

    assert res.getBody() != null;
    assert res.getBody().getContent() != null;

    assertThat(res.getStatusCode().value()).isEqualTo(200);

    String beforeFirstName = before.getFirstName();
    String beforeLastName = before.getLastName();
    String beforePhoneNumber = before.getPhoneNumber();

    Contact current = res.getBody().getContent();

    String currentFirstName = current.getFirstName();
    String currentLastName = current.getLastName();
    String currentPhoneNumber = current.getPhoneNumber();

    String currentId = current.getId();

    assertThat(beforeFirstName).isNotEqualTo(currentFirstName);
    assertThat(beforeLastName).isNotEqualTo(currentLastName);
    assertThat(beforePhoneNumber).isNotEqualTo(currentPhoneNumber);

    assertThat(id).isEqualTo(currentId);
  }

  @Test
  void testDeleteContact() {

    Contact contact = repo.findAll().getFirst();
    String id = contact.getId();

    final var req = RequestEntity.delete("/api/contacts/" + id).build();

    final var res = template.exchange(req, Void.class);

    assertThat(res.getStatusCode().value()).isEqualTo(204);
    assertThat(res.hasBody()).isFalse();
  }
}
