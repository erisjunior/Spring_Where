package com.erisvan.where.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erisvan.where.model.User;
import com.erisvan.where.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @RequestMapping("/createUserForm")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/createUser";
    }

    @RequestMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        userService.createUser(user);
        return getAllUsers(model);
    }

    @RequestMapping("/deleteUser/{idUser}")
    public String deleteUser(@PathVariable String idUser, Model model) {
        User user = userService.getUserById(Integer.parseInt(idUser));
        userService.deleteUser(user);
        return getAllUsers(model);
    }

    @RequestMapping("/getUser/{idUser}")
    public String getUser(@PathVariable String idUser, Model model) {
        User user = userService.getUserById(Integer.parseInt(idUser));
        model.addAttribute("user", user);
        model.addAttribute("hasAvatar", user.getAvatar() != null);
        return "user/getUser";
    }

    @RequestMapping("/getAllUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/getAllUsers";
    }
}
