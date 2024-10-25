package com.bsix.simplecontactapi.contact;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Address address;

    private String profilePicture;

    @Data
    @Builder
    @AllArgsConstructor
    private static class Address {

        private String street;

        private String city;

        private String state;

        private String country;

    }

}
