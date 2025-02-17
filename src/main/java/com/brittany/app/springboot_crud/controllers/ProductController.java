package com.brittany.app.springboot_crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.brittany.app.springboot_crud.services.ProductService;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    


}
