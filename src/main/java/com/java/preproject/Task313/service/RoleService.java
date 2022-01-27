package com.java.preproject.Task313.service;

import com.java.preproject.Task313.model.Role;

import java.util.List;

public interface RoleService {

    void saveRole(Role role);
    List<Role> getAllRoles();
    Role findRoleByRoleName(String RoleName);
}
