package com.brittany.app.springboot_crud.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brittany.app.springboot_crud.models.Product;
import com.brittany.app.springboot_crud.services.ProductService;
import com.brittany.app.springboot_crud.validation.CustomException.EntityNotFoundException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<Product>> getProductList() {
        return ResponseEntity.ok().body(productService.findAllProducts());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseThrow(() -> new EntityNotFoundException("El producto ".concat(id+" no existe o no está disponible")));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product) {
        Product newproduct = productService.createProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newproduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(newproduct);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> putProduct(@Valid @RequestBody Product product,
            @PathVariable Long id) {
        return productService.updateProduct(id, product)
                .map(productDb -> ResponseEntity.ok().body(productDb))
                .orElseThrow(() -> new EntityNotFoundException("El producto ".concat(id+" no existe o no está disponible")));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.deleteProductById(id);
        if (!productOptional.isPresent()) {
            throw new EntityNotFoundException("El producto ".concat(id+" no se encuentra registrado"));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
