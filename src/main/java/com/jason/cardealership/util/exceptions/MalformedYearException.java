package com.jason.cardealership.util.exceptions;

public class MalformedYearException extends RuntimeException {

  public MalformedYearException() {
    super("Year should be provided in 4-digit format: yyyy");
  }
}
