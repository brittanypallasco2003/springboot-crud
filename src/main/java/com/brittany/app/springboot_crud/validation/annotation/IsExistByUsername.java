package com.brittany.app.springboot_crud.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.brittany.app.springboot_crud.validation.validator.ExistByUsernameValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistByUsername {
    String message() default "ya se encuentra registrado!";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
