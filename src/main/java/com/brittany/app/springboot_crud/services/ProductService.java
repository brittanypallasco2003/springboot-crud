package com.brittany.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.brittany.app.springboot_crud.models.Product;

public interface ProductService {
    List<Product> findAllProducts();

    Optional<Product> findProductById(Long id);

    Product createProduct(Product product);

    Optional<Product> updateProduct(Long id, Product product);

    Optional<Product> deleteProductById(Long id);

    boolean existsBySku(String sku);

}
