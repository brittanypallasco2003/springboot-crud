package com.brittany.app.springboot_crud.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.brittany.app.springboot_crud.models.Product;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        if (product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null,"es requerido!");
        }
        if (product.getName() == null || product.getName().isBlank()) {
            errors.rejectValue("name", "es requerido!");
        }
        if (product.getPrice() == null) {
            errors.rejectValue("price", "no puede ser nulo!");
        }
        if(product.getPrice() < 0.10) {
            errors.rejectValue("price", "debe ser un valor numérico mayor o igual que 0.01!");

        }

    }

}
