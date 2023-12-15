package com.spring_basic_auth.basic_auth.repository;

import com.spring_basic_auth.basic_auth.model.Sales;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SalesRepository extends CrudRepository<Sales,Integer> {


}
