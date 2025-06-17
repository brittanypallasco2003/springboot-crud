package com.brittany.app.springboot_crud.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brittany.app.springboot_crud.services.ProductService;
import com.brittany.app.springboot_crud.validation.annotation.IsExistsDb;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

    @Autowired
    private ProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (productService != null) {
            return !productService.existsBySku(value);
        }
        return true;
    }

}
