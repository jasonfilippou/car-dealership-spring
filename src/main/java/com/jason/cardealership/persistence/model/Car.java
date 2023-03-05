package com.jason.cardealership.persistence.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {

  private final String vin;
  private final String make;
  private final String model;
  private final Integer year;
  private final Long costInUSD;
}
