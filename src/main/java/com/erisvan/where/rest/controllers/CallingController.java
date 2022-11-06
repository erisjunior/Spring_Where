package com.erisvan.where.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erisvan.where.model.Calling;
import com.erisvan.where.model.Client;
import com.erisvan.where.service.CallingService;
import com.erisvan.where.service.UserService;

@Controller
@RequestMapping("/calling")
public class CallingController {

    @Autowired
    @Qualifier("callingServiceImpl")
    CallingService callingService;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @RequestMapping("/createCallingForm")
    public String createCallingForm(Model model) {
        model.addAttribute("calling", new Calling());
        model.addAttribute("users", userService.getAllUsers());
        return "calling/createCalling";
    }

    @RequestMapping("/createCallingFormFromUser/{idUser}")
    public String createCallingForm(@PathVariable String idUser, Model model) {
        Client user = userService.getUserById(Integer.parseInt(idUser));
        Calling calling = new Calling();
        calling.setUser(user);

        model.addAttribute("calling", calling);
        model.addAttribute("users", userService.getAllUsers());
        return "calling/createCalling";
    }

    @RequestMapping("/createCalling")
    public String createCalling(@ModelAttribute("calling") Calling calling, Model model) {
        callingService.createCalling(calling);
        return getAllCallings(model);
    }

    @RequestMapping("/deleteCalling/{idCalling}")
    public String deleteCalling(@PathVariable String idCalling, Model model) {
        Calling calling = callingService.getCallingById(Integer.parseInt(idCalling));
        callingService.deleteCalling(calling);
        return getAllCallings(model);
    }

    @RequestMapping("/getCalling/{idCalling}")
    public String getCalling(@PathVariable String idCalling, Model model) {
        Calling calling = callingService.getCallingById(Integer.parseInt(idCalling));
        model.addAttribute("calling", calling);
        return "calling/getCalling";
    }

    @RequestMapping("/getAllCallings")
    public String getAllCallings(Model model) {
        model.addAttribute("callings", callingService.getAllCallings());
        return "calling/getAllCallings";
    }
}
