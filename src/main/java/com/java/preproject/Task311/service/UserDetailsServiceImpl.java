package com.java.preproject.Task311.service;

import com.java.preproject.Task311.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.java.preproject.Task311.model.User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User name is null");
        }
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}

