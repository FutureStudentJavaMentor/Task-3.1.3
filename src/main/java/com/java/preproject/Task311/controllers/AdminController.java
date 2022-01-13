package com.java.preproject.Task311.controllers;

import com.java.preproject.Task311.model.Role;
import com.java.preproject.Task311.model.User;
import com.java.preproject.Task311.service.RoleService;
import com.java.preproject.Task311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "BootstrapUsers";
    }

    @GetMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("AllRoles", roleService.getAllRoles());
        return "BootstrapUsers";
    }


    @PostMapping("/newUser")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "newUser";
        }
        Role role1;
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            role1 = roleService.findRoleByRoleName(role.getName());
            roleSet.add(role1);
        }
        user.setRoles(roleSet);
        userService.saveNewUser(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.findById(id).orElse(new User()));
        model.addAttribute("AllRoles", roleService.getAllRoles());
        return "BootstrapUsers";
    }

    @PostMapping("/updateUser/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "BootstrapUsers";
        }
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role role2 = roleService.findRoleByRoleName(role.getName());
            roleSet.add(role2);
        }
        user.setRoles(roleSet);

        userService.saveNewUser(user);

        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }


}
