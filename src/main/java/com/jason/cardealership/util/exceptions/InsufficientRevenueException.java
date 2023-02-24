package com.jason.cardealership.util.exceptions;

public class InsufficientRevenueException extends RuntimeException {

  public InsufficientRevenueException() {
    super("Not enough revenue in dealership!");
  }
}
