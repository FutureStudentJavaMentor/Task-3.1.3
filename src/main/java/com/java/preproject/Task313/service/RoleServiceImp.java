package com.java.preproject.Task313.service;

import com.java.preproject.Task313.model.Role;
import com.java.preproject.Task313.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleByRoleName(String RoleName) {
        if (RoleName.contains("ADMIN")) {
            RoleName ="ROLE_ADMIN";
        }else if(RoleName.contains("USER")){
            RoleName = "ROLE_USER";
        }
        return roleRepository.findByName(RoleName);
    }


}
