package com.jason.cardealership.util;

import com.jason.cardealership.util.exceptions.MalformedYearException;
import org.apache.commons.lang3.StringUtils;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DealershipUtils {

  private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH);

  public static int parseYearToInt(String year) {
    try {
      return Year.parse(StringUtils.strip(year), FMT).getValue();
    } catch (DateTimeParseException exc) {
      throw new MalformedYearException();
    }
  }
}
