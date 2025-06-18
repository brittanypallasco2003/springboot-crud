package com.brittany.app.springboot_crud.validation.CustomException;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
