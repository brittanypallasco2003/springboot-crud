package com.brittany.app.springboot_crud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittany.app.springboot_crud.models.Role;
import com.brittany.app.springboot_crud.models.User;
import com.brittany.app.springboot_crud.repositories.RoleRepository;
import com.brittany.app.springboot_crud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();

    }

    @Transactional
    @Override
    public User registerUsers(User user) {
        Optional<Role> optionalRoleUser= roleRepository.findByName("ROLE_USER");
        List<Role> roles= new ArrayList<>();
        optionalRoleUser.ifPresent(role->roles.add(role));

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin= roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(role->roles.add(role));
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
