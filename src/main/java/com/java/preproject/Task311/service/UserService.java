package com.java.preproject.Task311.service;

import com.java.preproject.Task311.model.User;
import com.java.preproject.Task311.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder1) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder1;
    }

    public void saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getUsersList() {
        return userRepository.findAll();
    }

     public Optional<User> findById(Long id) {
        return userRepository.findById(id) ;
    }


     public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

     public User findByUserName(String email) {
        return userRepository.findByEmail(email);
    }

}
