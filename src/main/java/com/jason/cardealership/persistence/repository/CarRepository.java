package com.jason.cardealership.persistence.repository;

import com.jason.cardealership.persistence.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

  void removeFromRepository(String vin);

  void addToRepository(Car car);

  Optional<Car> findByVin(String vin);

  List<Car> findByMake(String make);

  List<Car> findByMakeAndModel(String make, String model);

  List<Car> findByMakeModelAndYear(String make, String model, int year);

  List<Car> findByYearRange(int lowerYearInclusive, int higherYearInclusive);

  List<Car> findInCostRange(long lowerCostInclusive, long higherCostInclusive);
}
