package com.brittany.app.springboot_crud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.brittany.app.springboot_crud.models.Role;

public interface RoleRepository extends CrudRepository <Role, Long> {

    Optional<Role> findByName(String name);
}
