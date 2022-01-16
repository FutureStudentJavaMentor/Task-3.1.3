package com.java.preproject.Task311.service;

import com.java.preproject.Task311.model.Role;
import com.java.preproject.Task311.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
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
