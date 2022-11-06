package com.erisvan.where.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erisvan.where.model.Avatar;
import com.erisvan.where.model.Client;
import com.erisvan.where.service.AvatarService;
import com.erisvan.where.service.UserService;

@Controller
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    @Qualifier("avatarServiceImpl")
    AvatarService avatarService;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @RequestMapping("/createAvatarFormFromUser/{idUser}")
    public String createAvatarForm(@PathVariable String idUser, Model model) {
        Client user = userService.getUserById(Integer.parseInt(idUser));
        Avatar avatar = new Avatar();
        avatar.setUser(user);

        model.addAttribute("avatar", avatar);
        return "avatar/createAvatar";
    }

    @RequestMapping("/createAvatar")
    public String createAvatar(@ModelAttribute("avatar") Avatar avatar, Model model) {
        avatarService.createAvatar(avatar);
        model.addAttribute("users", userService.getAllUsers());
        return "user/getAllUsers";
    }
}
