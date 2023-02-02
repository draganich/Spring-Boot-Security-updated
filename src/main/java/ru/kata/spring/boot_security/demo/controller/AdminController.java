package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


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

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.userList());
        return "admin";
    }

    @GetMapping("/create")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("rolesList", roleService.roleList());
        return "create";
    }
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("rolesList", roleService.roleList());
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("rolesList", roleService.roleList());
        model.addAttribute("chosenUser", user);
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("rolesList", roleService.roleList());
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}




