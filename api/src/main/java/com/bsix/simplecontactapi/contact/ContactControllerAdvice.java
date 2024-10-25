package com.bsix.simplecontactapi.contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContactControllerAdvice {

  @ExceptionHandler(ContactNotFoundException.class)
  ResponseEntity<ProblemDetail> contactNotFoundHandler(ContactNotFoundException ex) {
    ProblemDetail detail = ProblemDetail.forStatus(HttpStatusCode.valueOf(404));
    detail.setTitle("Contact Not Found");
    detail.setDetail(ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detail);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<ProblemDetail> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
    ProblemDetail detail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
    detail.setTitle("Illegal Request Parameter");
    detail.setDetail(ex.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
  }
}
