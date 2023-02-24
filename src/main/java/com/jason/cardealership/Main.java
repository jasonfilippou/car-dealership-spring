package com.jason.cardealership;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

  public static void main(String[] args) {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH);
    System.out.println(Year.parse("2014", fmt).getValue());
  }
}
