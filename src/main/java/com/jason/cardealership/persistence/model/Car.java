package com.jason.cardealership.persistence.model;

import lombok.Data;

@Data
public class Car {

  private String vin;
  private String make;
  private String model;
  private int year;
  private long costInUSD;
}
