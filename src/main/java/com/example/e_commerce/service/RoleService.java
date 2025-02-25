package com.example.e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.e_commerce.entities.Role;

@Service
public interface RoleService {
    List<Role> getAllRoles();

    Role createRole(Role role);

    Role getRoleById(Long id);

    Role updateRole(Long id, Role role);

    void deleteRole(Long id);
}
