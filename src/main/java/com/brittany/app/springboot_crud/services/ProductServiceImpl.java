package com.brittany.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittany.app.springboot_crud.models.Product;
import com.brittany.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> deleteProductById(Long id) {
        Optional<Product> productOptional= productRepository.findById(id);
        productOptional.ifPresent(productDb->{
            productRepository.deleteById(id);
        });
        return productOptional;
    }

    @Transactional
    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
       if (productOptional.isPresent()) {
        Product productDb = productOptional.get();
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setDescription(product.getDescription());
        return Optional.of(productDb);
       }
       return productOptional;
    }

}
