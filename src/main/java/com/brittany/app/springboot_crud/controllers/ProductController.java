package com.brittany.app.springboot_crud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.brittany.app.springboot_crud.models.Product;
import com.brittany.app.springboot_crud.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProductList() {
        return productService.findAllProducts(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
        .map(product -> ResponseEntity.ok().body(product))
        .orElseGet(() -> ResponseEntity.notFound().build());
        
    }

    @PostMapping
    public Product addProduct(@RequestBody Product newProduct) {
        return productService.createProduct(newProduct);
    }
    


   
    
    


}
