package com.java.preproject.Task311.controllers;

import com.java.preproject.Task311.model.Role;
import com.java.preproject.Task311.model.User;
import com.java.preproject.Task311.service.RoleService;
import com.java.preproject.Task311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/users")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "BootstrapAdminPage";
    }

    @GetMapping(value = "/newUser")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("AllRoles", roleService.getAllRoles());
        return "BootstrapAdminPage";
    }


    @PostMapping(value = "/newUser")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(name = "RoleName", required = false) String[] RoleName) {
        if (RoleName != null) {
            Set<Role> roleSet = new HashSet<>();
            for (String name : RoleName) {
                roleSet.add(roleService.findRoleByRoleName(name));
            }
            user.setRoles(roleSet);
        }
        userService.saveNewUser(user);
        return "redirect:/admin/users";
    }


    @GetMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.findById(id).orElse(new User()));
        model.addAttribute("AllRoles", roleService.getAllRoles());
        return "BootstrapAdminPage";
    }

    @PostMapping(value = "/updateUser/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam(name = "RoleName", required = false) String[] RoleName) {
        if (RoleName != null) {
            Set<Role> roleSet = new HashSet<>();
            for (String name : RoleName) {
                roleSet.add(roleService.findRoleByRoleName(name));
            }
            user.setRoles(roleSet);
        }
        userService.saveNewUser(user);

        return "redirect:/admin/users";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }


}
