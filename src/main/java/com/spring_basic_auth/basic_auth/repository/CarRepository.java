package com.spring_basic_auth.basic_auth.repository;

import com.spring_basic_auth.basic_auth.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car,Integer> {
}
