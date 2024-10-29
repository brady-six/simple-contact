package com.bsix.sca;

import com.bsix.sca.contact.Contact;
import com.bsix.sca.contact.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration()
@Profile("test")
public class Seed {

  @Bean
  public CommandLineRunner runner(ContactRepository contactRepository) {
    return args -> {
      contactRepository.save(
          new Contact(
              "John",
              "Doe",
              "123-456-7890",
              "profile1.jpg",
              new Contact.Address("123 Main St", "Any Town", "CA", "12345", "USA")));
      contactRepository.save(
          new Contact(
              "Jane",
              "Smith",
              "234-567-8901",
              "profile2.jpg",
              new Contact.Address("456 Oak St", "Sample City", "TX", "23456", "USA")));
      contactRepository.save(
          new Contact(
              "Mary",
              "Johnson",
              "345-678-9012",
              "profile3.jpg",
              new Contact.Address("789 Pine St", "Demo Village", "NY", "34567", "USA")));
      contactRepository.save(
          new Contact(
              "Michael",
              "Williams",
              "456-789-0123",
              "profile4.jpg",
              new Contact.Address("101 Maple St", "Example City", "FL", "45678", "USA")));
      contactRepository.save(
          new Contact(
              "Emily",
              "Brown",
              "567-890-1234",
              "profile5.jpg",
              new Contact.Address("202 Birch St", "Testville", "IL", "56789", "USA")));
      contactRepository.save(
          new Contact(
              "Chris",
              "Jones",
              "678-901-2345",
              "profile6.jpg",
              new Contact.Address("303 Cedar St", "Any Town", "CA", "67890", "USA")));
      contactRepository.save(
          new Contact(
              "Katie",
              "Garcia",
              "789-012-3456",
              "profile7.jpg",
              new Contact.Address("404 Elm St", "Sample City", "TX", "78901", "USA")));
      contactRepository.save(
          new Contact(
              "David",
              "Miller",
              "890-123-4567",
              "profile8.jpg",
              new Contact.Address("505 Spruce St", "Demo Village", "NY", "89012", "USA")));
      contactRepository.save(
          new Contact(
              "Sarah",
              "Davis",
              "901-234-5678",
              "profile9.jpg",
              new Contact.Address("123 Main St", "Example City", "FL", "90123", "USA")));
      contactRepository.save(
          new Contact(
              "Robert",
              "Rodriguez",
              "012-345-6789",
              "profile10.jpg",
              new Contact.Address("456 Oak St", "Testville", "IL", "01234", "USA")));
      contactRepository.save(
          new Contact(
              "Laura",
              "Hernandez",
              "123-456-7891",
              "profile11.jpg",
              new Contact.Address("789 Pine St", "Any Town", "CA", "12346", "USA")));
      contactRepository.save(
          new Contact(
              "Daniel",
              "Martinez",
              "234-567-8902",
              "profile12.jpg",
              new Contact.Address("101 Maple St", "Sample City", "TX", "23457", "USA")));
      contactRepository.save(
          new Contact(
              "Jessica",
              "Lopez",
              "345-678-9013",
              "profile13.jpg",
              new Contact.Address("202 Birch St", "Demo Village", "NY", "34568", "USA")));
      contactRepository.save(
          new Contact(
              "Brian",
              "Gonzalez",
              "456-789-0124",
              "profile14.jpg",
              new Contact.Address("303 Cedar St", "Example City", "FL", "45679", "USA")));
      contactRepository.save(
          new Contact(
              "Amanda",
              "Wilson",
              "567-890-1235",
              "profile15.jpg",
              new Contact.Address("404 Elm St", "Testville", "IL", "56780", "USA")));
      contactRepository.save(
          new Contact(
              "Charles",
              "Anderson",
              "678-901-2346",
              "profile16.jpg",
              new Contact.Address("505 Spruce St", "Any Town", "CA", "67891", "USA")));
      contactRepository.save(
          new Contact(
              "Elizabeth",
              "Thomas",
              "789-012-3457",
              "profile17.jpg",
              new Contact.Address("123 Main St", "Sample City", "TX", "78902", "USA")));
      contactRepository.save(
          new Contact(
              "Kevin",
              "Taylor",
              "890-123-4568",
              "profile18.jpg",
              new Contact.Address("456 Oak St", "Demo Village", "NY", "89013", "USA")));
      contactRepository.save(
          new Contact(
              "Nancy",
              "Moore",
              "901-234-5679",
              "profile19.jpg",
              new Contact.Address("789 Pine St", "Example City", "FL", "90124", "USA")));
      contactRepository.save(
          new Contact(
              "Paul",
              "Jackson",
              "012-345-6780",
              "profile20.jpg",
              new Contact.Address("101 Maple St", "Testville", "IL", "01235", "USA")));
      contactRepository.save(
          new Contact(
              "Karen",
              "Martin",
              "123-456-7892",
              "profile21.jpg",
              new Contact.Address("202 Birch St", "Any Town", "CA", "12347", "USA")));
      contactRepository.save(
          new Contact(
              "Mark",
              "Lee",
              "234-567-8903",
              "profile22.jpg",
              new Contact.Address("303 Cedar St", "Sample City", "TX", "23458", "USA")));
      contactRepository.save(
          new Contact(
              "Samantha",
              "Perez",
              "345-678-9014",
              "profile23.jpg",
              new Contact.Address("404 Elm St", "Demo Village", "NY", "34569", "USA")));
      contactRepository.save(
          new Contact(
              "Steven",
              "Thompson",
              "456-789-0125",
              "profile24.jpg",
              new Contact.Address("505 Spruce St", "Example City", "FL", "45680", "USA")));
      contactRepository.save(
          new Contact(
              "Megan",
              "White",
              "567-890-1236",
              "profile25.jpg",
              new Contact.Address("123 Main St", "Testville", "IL", "56781", "USA")));
      contactRepository.save(
          new Contact(
              "Jacob",
              "Harris",
              "678-901-2347",
              "profile26.jpg",
              new Contact.Address("456 Oak St", "Any Town", "CA", "67892", "USA")));
      contactRepository.save(
          new Contact(
              "Emily",
              "Clark",
              "789-012-3458",
              "profile27.jpg",
              new Contact.Address("789 Pine St", "Sample City", "TX", "78903", "USA")));
      contactRepository.save(
          new Contact(
              "Matthew",
              "Lewis",
              "890-123-4569",
              "profile28.jpg",
              new Contact.Address("101 Maple St", "Demo Village", "NY", "89014", "USA")));
      contactRepository.save(
          new Contact(
              "Olivia",
              "Robinson",
              "901-234-5670",
              "profile29.jpg",
              new Contact.Address("202 Birch St", "Example City", "FL", "90125", "USA")));
      contactRepository.save(
          new Contact(
              "Joshua",
              "Walker",
              "012-345-6781",
              "profile30.jpg",
              new Contact.Address("303 Cedar St", "Testville", "IL", "01236", "USA")));
      contactRepository.save(
          new Contact(
              "Sophia",
              "Hall",
              "123-456-7893",
              "profile31.jpg",
              new Contact.Address("404 Elm St", "Any Town", "CA", "12348", "USA")));
      contactRepository.save(
          new Contact(
              "Andrew",
              "Young",
              "234-567-8904",
              "profile32.jpg",
              new Contact.Address("505 Spruce St", "Sample City", "TX", "23459", "USA")));
      contactRepository.save(
          new Contact(
              "Ava",
              "Hernandez",
              "345-678-9015",
              "profile33.jpg",
              new Contact.Address("123 Main St", "Demo Village", "NY", "34570", "USA")));
      contactRepository.save(
          new Contact(
              "Ethan",
              "King",
              "456-789-0126",
              "profile34.jpg",
              new Contact.Address("456 Oak St", "Example City", "FL", "45681", "USA")));
      contactRepository.save(
          new Contact(
              "Isabella",
              "Scott",
              "567-890-1237",
              "profile35.jpg",
              new Contact.Address("789 Pine St", "Testville", "IL", "56782", "USA")));
      contactRepository.save(
          new Contact(
              "David",
              "Green",
              "678-901-2348",
              "profile36.jpg",
              new Contact.Address("101 Maple St", "Any Town", "CA", "67893", "USA")));
    };
  }
}
