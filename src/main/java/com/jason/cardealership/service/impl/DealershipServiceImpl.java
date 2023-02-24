package com.jason.cardealership.service.impl;

import com.jason.cardealership.DealershipService;
import com.jason.cardealership.persistence.model.Car;
import com.jason.cardealership.persistence.model.repository.CarRepository;
import com.jason.cardealership.util.exceptions.InsufficientRevenueException;
import com.jason.cardealership.util.exceptions.MalformedYearException;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeParseException;
import java.util.List;

import static com.jason.cardealership.util.DealershipUtils.parseYearToInt;

@Slf4j
public class DealershipServiceImpl implements DealershipService {

  private long totalRevenue;
  private final CarRepository carRepository;

  public DealershipServiceImpl(long initialRevenue, CarRepository carRepository) {
    this.totalRevenue = initialRevenue;
    this.carRepository = carRepository;
  }

  @Override
  public void sell(Car car) {
    carRepository.addToRepository(car);
    totalRevenue += car.getCostInUSD();
    log.info("Sold car {}. Current revenue: {}", car, totalRevenue);
  }

  @Override
  public void buy(Car car) {
    if (totalRevenue >= car.getCostInUSD()) {
      carRepository.addToRepository(car);
      totalRevenue -= car.getCostInUSD();
      log.info("Bought car {}. Current revenue: {}", car, totalRevenue);
    } else {
      log.warn("Unable to buy car {}; we're broke!", car);
      throw new InsufficientRevenueException();
    }
  }

  @Override
  public List<Car> findByMake(String make) {
    log.info("Asking repo for all cars of make: {}", make);
    return carRepository.findByMake(make);
  }

  @Override
  public List<Car> findByMakeAndModel(String make, String model) {
    log.info("Asking repo for all cars of make: {} and model: {}", make, model);
    return carRepository.findByMakeAndModel(make, model);
  }

  @Override
  public List<Car> findByMakeModelAndYear(String make, String model, String year) {
    try {
      int yearParsed = parseYearToInt(year);
      log.info(
          "Asking repo for all cars of make: {}, model:{} and year: {}", make, model, yearParsed);
      return carRepository.findByMakeModelAndYear(make, model, yearParsed);
    } catch (DateTimeParseException exc) {
      log.warn("Unable to parse year {}", year);
      throw new MalformedYearException();
    }
  }

  @Override
  public List<Car> findByYearRange(String yearLowInclusive, String yearHighInclusive) {
    try {
      int yearLowParsed = parseYearToInt(yearLowInclusive);
      int yearHighParsed = parseYearToInt(yearHighInclusive);
      log.info(
          "Asking repo for all cars made between {} and {} inclusive...",
          yearLowParsed,
          yearHighParsed);
      return carRepository.findByYearRange(yearLowParsed, yearHighParsed);
    } catch (DateTimeParseException exc) {
      throw new MalformedYearException();
    }
  }

  @Override
  public List<Car> findInCostRange(long costLowInclusive, long costHighInclusive) {
    if (costLowInclusive > costHighInclusive) {
      log.warn("Provided invalid cost interval: [{},{}]", costLowInclusive, costHighInclusive);
      throw new IllegalArgumentException(
          "Provide a smaller bound and a higher bound for cost. Given: "
              + costLowInclusive
              + ", "
              + costHighInclusive);
    }
    log.info(
        "Asking repo for all cars costing between {} and {} inclusive...",
        costLowInclusive,
        costHighInclusive);
    return carRepository.findInCostRange(costLowInclusive, costHighInclusive);
  }
}
