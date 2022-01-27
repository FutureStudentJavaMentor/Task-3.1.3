package com.java.preproject.Task313.controllers;

import com.java.preproject.Task313.model.User;
import com.java.preproject.Task313.service.RoleService;
import com.java.preproject.Task313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/user_info")
    public String getUserInfo(Authentication authentication, Model model) {
        User user = userService.findByUserName(authentication.getName());
        model.addAttribute("user", user);
        return "UserPage";
    }

    @GetMapping(value = "/admin")
    public String listUsers(Authentication user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.getUsersList());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "AdminPage";
    }
}

