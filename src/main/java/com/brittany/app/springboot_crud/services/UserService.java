package com.brittany.app.springboot_crud.services;

import java.util.List;

import com.brittany.app.springboot_crud.models.User;

public interface UserService {
    List<User> getAllUsers();
    
    User registerUsers(User user);

    boolean existsByUsername(String username);
}
