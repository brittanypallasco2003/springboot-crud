package com.brittany.app.springboot_crud.services;

import java.util.List;

import com.brittany.app.springboot_crud.models.User;

public interface UserService {
    List<User> getAllUsers();
    User registerUser(User user);

}
