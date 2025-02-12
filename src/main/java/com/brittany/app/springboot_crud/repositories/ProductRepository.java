package com.brittany.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.brittany.app.springboot_crud.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
