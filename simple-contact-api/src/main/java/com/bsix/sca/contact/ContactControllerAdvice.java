package com.bsix.sca.contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContactControllerAdvice {

  @ExceptionHandler(ContactNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleContactNotFound(ContactNotFoundException ex) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problemDetail.setTitle("Contact Not Found");
    problemDetail.setDetail(ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
  }
}
