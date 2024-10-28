package com.bsix.sca.contact;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "contacts")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String firstName;

  private String lastName;

  private String phoneNumber;

  private String profilePicture;

  private Address address;

  public Contact(
      String firstName,
      String lastName,
      String phoneNumber,
      String profilePicture,
      Address address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.profilePicture = profilePicture;
    this.address = address;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Embeddable
  public static class Address {

    private String street;

    private String city;

    private String state;

    private String zipcode;

    private String country;
  }
}
