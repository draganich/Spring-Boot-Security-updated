package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> roleList();

    void save(Role role);

    Role findRoleByName(String name);
}

