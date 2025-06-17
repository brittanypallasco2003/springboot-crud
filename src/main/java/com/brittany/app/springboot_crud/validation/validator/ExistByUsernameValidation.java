package com.brittany.app.springboot_crud.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brittany.app.springboot_crud.services.UserService;
import com.brittany.app.springboot_crud.validation.annotation.IsExistByUsername;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<IsExistByUsername, String> {

    @Autowired
    private UserService userService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (userService !=null) {
            return !userService.existsByUsername(value);
        }
        return true;
    }

}
