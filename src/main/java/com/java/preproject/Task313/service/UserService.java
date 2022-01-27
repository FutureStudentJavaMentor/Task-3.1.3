package com.java.preproject.Task313.service;

import com.java.preproject.Task313.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveOrUpdate(User user);

    List<User> getUsersList();

    Optional<User> findById(Long id);

    void deleteUser(Long id);

    User findByUserName(String email);
}
