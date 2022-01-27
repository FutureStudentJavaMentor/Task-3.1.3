package com.java.preproject.Task313;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.java.preproject.Task313.model.Role;
import com.java.preproject.Task313.model.User;
import com.java.preproject.Task313.service.RoleServiceImp;
import com.java.preproject.Task313.service.UserServiceImp;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DoAfterLoadProject {


    private final UserServiceImp userService;
    private final RoleServiceImp roleService;


    @Autowired
    public DoAfterLoadProject(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    @PostConstruct
    public void init() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleUser);
        roleSet.add(roleAdmin);


        user1.setRoles(Collections.singleton(roleUser));
        user1.setEmail("user1@mail.ru");
        user1.setPassword("100");
        user1.setAge(25);
        user1.setName("user_name_1");
        user1.setLastName("user_female_1");

        user2.setRoles(Collections.singleton(roleAdmin));
        user2.setEmail("user2@mail.ru");
        user2.setPassword("100");
        user2.setAge(27);
        user2.setName("user_name_2");
        user2.setLastName("user_female_2");


        user3.setRoles(roleSet);
        user3.setEmail("user3@mail.ru");
        user3.setPassword("100");
        user3.setAge(27);
        user3.setName("user_name_3");
        user3.setLastName("user_female_3");


        userService.saveOrUpdate(user1);
        userService.saveOrUpdate(user2);
        userService.saveOrUpdate(user3);

    }


}
