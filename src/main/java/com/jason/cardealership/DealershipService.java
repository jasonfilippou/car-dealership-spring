package com.jason.cardealership;

import com.jason.cardealership.persistence.model.Car;

import java.util.List;

public interface DealershipService {

  void sell(Car car);

  void buy(Car car);

  List<Car> findByMake(String make);

  List<Car> findByMakeAndModel(String make, String model);

  List<Car> findByMakeModelAndYear(String make, String model, String year);

  List<Car> findByYearRange(String yearLowInclusive, String yearHighInclusive);

  List<Car> findInCostRange(long costLowInclusive, long costHighInclusive);
}
