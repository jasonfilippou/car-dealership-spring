package com.jason.cardealership.persistence.repository;

import com.google.common.collect.Lists;
import com.jason.cardealership.persistence.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Profile("dev")
public class CarRepositoryImpl implements CarRepository {

  private final ArrayList<Car> cars = Lists.newArrayList();

  @Override
  public void removeFromRepository(String vin) {
    Optional<Car> toRemove = findByVin(vin);
    toRemove.ifPresent(
        car -> {
          log.info("Removing car {}", car);
          cars.remove(car);
        });
  }

  @Override
  public void addToRepository(Car car) {
    // Make sure we don't add dups
    Optional<Car> possibleDuplicate = findByVin(car.getVin());
    if (possibleDuplicate.isEmpty()) {
      log.info("Adding car {}", car);
      cars.add(car);
    } else {
      log.info("Car with VIN#: {} already in repo", car.getVin());
    }
  }

  @Override
  public Optional<Car> findByVin(String vin) {
    return cars.stream().filter(car -> car.getVin().equals(vin)).findFirst();
  }

  @Override
  public List<Car> findByMake(String make) {
    return cars.stream().filter(car -> car.getMake().equals(make)).collect(Collectors.toList());
  }

  @Override
  public List<Car> findByMakeAndModel(String make, String model) {
    return cars.stream()
        .filter(car -> car.getMake().equals(make) && car.getModel().equals(model))
        .collect(Collectors.toList());
  }

  @Override
  public List<Car> findByMakeModelAndYear(String make, String model, int year) {
    return cars.stream()
        .filter(
            car ->
                car.getMake().equals(make) && car.getModel().equals(model) && car.getYear() == year)
        .collect(Collectors.toList());
  }

  @Override
  public List<Car> findByYearRange(int lowerYearInclusive, int higherYearInclusive) {
    return cars.stream()
        .filter(car -> car.getYear() >= lowerYearInclusive && car.getYear() <= higherYearInclusive)
        .collect(Collectors.toList());
  }

  @Override
  public List<Car> findInCostRange(long lowerCostInclusive, long higherCostInclusive) {
    return cars.stream()
        .filter(
            car ->
                car.getCostInUSD() >= lowerCostInclusive
                    && car.getCostInUSD() <= higherCostInclusive)
        .collect(Collectors.toList());
  }
}
