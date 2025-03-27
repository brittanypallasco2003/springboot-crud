package com.brittany.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brittany.app.springboot_crud.models.User;

public interface UserRepository extends CrudRepository<User,Long> {

    boolean existsByUsername(String username);
}