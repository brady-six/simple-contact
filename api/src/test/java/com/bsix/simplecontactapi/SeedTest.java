package com.bsix.simplecontactapi;

import com.bsix.simplecontactapi.contact.Contact;
import com.bsix.simplecontactapi.contact.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class SeedTest {

  private static final Logger log = LoggerFactory.getLogger(SeedTest.class);

  @Bean
  public CommandLineRunner commandLineRunner(ContactRepository repository) {
    return args -> {
      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "1",
                  "John",
                  "Doe",
                  "1234567890",
                  new Contact.Address("100 NW Lombok Lane", "Seattle", "WA", "12095", "USA"),
                  "john.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "2",
                  "Jane",
                  "Smith",
                  "0987654321",
                  new Contact.Address("200 NE Bali St", "Portland", "OR", "97201", "USA"),
                  "jane.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "3",
                  "Alice",
                  "Johnson",
                  "5551234567",
                  new Contact.Address("300 S Maui Blvd", "Los Angeles", "CA", "90001", "USA"),
                  "alice.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "4",
                  "Bob",
                  "Brown",
                  "4449876543",
                  new Contact.Address("400 E Fiji Rd", "Miami", "FL", "33101", "USA"),
                  "bob.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "5",
                  "Charlie",
                  "Davis",
                  "3336547890",
                  new Contact.Address("500 W Samoa Ave", "Chicago", "IL", "60601", "USA"),
                  "charlie.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "6",
                  "Diana",
                  "Wilson",
                  "2227654321",
                  new Contact.Address("600 NW Tonga Blvd", "Houston", "TX", "77001", "USA"),
                  "diana.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "7",
                  "Ethan",
                  "Martinez",
                  "1112345678",
                  new Contact.Address("700 NE Cook St", "San Francisco", "CA", "94101", "USA"),
                  "ethan.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "8",
                  "Fiona",
                  "Garcia",
                  "8888765432",
                  new Contact.Address("800 S Oahu Ave", "New York", "NY", "10001", "USA"),
                  "fiona.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "9",
                  "George",
                  "Anderson",
                  "7773456789",
                  new Contact.Address("900 E Malta Dr", "Phoenix", "AZ", "85001", "USA"),
                  "george.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "10",
                  "Hannah",
                  "Taylor",
                  "6661237890",
                  new Contact.Address("1010 W Fiji Ct", "San Diego", "CA", "92101", "USA"),
                  "hannah.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "11",
                  "Isaac",
                  "Newton",
                  "1234567891",
                  new Contact.Address("1 Apple St", "Cambridge", "MA", "02139", "USA"),
                  "isaac.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "12",
                  "Marie",
                  "Curie",
                  "1234567892",
                  new Contact.Address("2 Radioactive Rd", "Paris", "France", "75001", "France"),
                  "marie.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "13",
                  "Albert",
                  "Einstein",
                  "1234567893",
                  new Contact.Address("3 Theory Ln", "Princeton", "NJ", "08540", "USA"),
                  "albert.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "14",
                  "Galileo",
                  "Galilei",
                  "1234567894",
                  new Contact.Address("4 Telescope Way", "Florence", "Italy", "50100", "Italy"),
                  "galileo.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "15",
                  "Nikola",
                  "Tesla",
                  "1234567895",
                  new Contact.Address("5 Electricity Blvd", "New York", "NY", "10001", "USA"),
                  "nikola.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "16",
                  "Ada",
                  "Lovelace",
                  "1234567896",
                  new Contact.Address("6 Algorithm St", "London", "UK", "EC1A", "UK"),
                  "ada.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "17",
                  "Leonardo",
                  "da Vinci",
                  "1234567897",
                  new Contact.Address("7 Renaissance Ave", "Florence", "Italy", "50100", "Italy"),
                  "leonardo.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "18",
                  "Thomas",
                  "Edison",
                  "1234567898",
                  new Contact.Address("8 Invention Rd", "Menlo Park", "NJ", "08057", "USA"),
                  "thomas.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "19",
                  "Stephen",
                  "Hawking",
                  "1234567899",
                  new Contact.Address("9 Cosmos Ct", "Cambridge", "UK", "CB3", "UK"),
                  "stephen.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "20",
                  "Carl",
                  "Sagan",
                  "1234567810",
                  new Contact.Address("10 Cosmos Blvd", "Ithaca", "NY", "14850", "USA"),
                  "carl.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "21",
                  "Jane",
                  "Goodall",
                  "1234567811",
                  new Contact.Address(
                      "11 Primate Ln", "Gombe", "Tanzania", "P.O. Box 123", "Tanzania"),
                  "jane.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "22",
                  "Rosalind",
                  "Franklin",
                  "1234567812",
                  new Contact.Address("12 DNA St", "London", "UK", "WC1H", "UK"),
                  "rosalind.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "23",
                  "Margaret",
                  "Hamilton",
                  "1234567813",
                  new Contact.Address("13 Software Rd", "Cambridge", "MA", "02142", "USA"),
                  "margaret.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "24",
                  "Baba",
                  "Adekola",
                  "1234567814",
                  new Contact.Address("14 Wisdom Ave", "Lagos", "Nigeria", "101001", "Nigeria"),
                  "baba.png")));

      log.info(
          "Preloading {}",
          repository.save(
              new Contact(
                  "25",
                  "Chinua",
                  "Achebe",
                  "1234567815",
                  new Contact.Address("15 Literature Ln", "Enugu", "Nigeria", "400001", "Nigeria"),
                  "chinua.png")));
    };
  }
}
