package com.spring_basic_auth.basic_auth.repository;

import com.spring_basic_auth.basic_auth.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Integer> {
}
