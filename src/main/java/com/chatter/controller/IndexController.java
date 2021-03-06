package com.chatter.controller;

import com.chatter.domain.UserDto;
import com.chatter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/home"})
    public String showHome() {
        return "home";
    }

    @RequestMapping(value = "/login")
    public String showLogin() { return "login"; }
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser() {
        return "redirect:/board/list";
    }

    @RequestMapping("/logout")
    public String logoutUser() { return "redirect:/home"; }

    @RequestMapping(value = "/register")
    public String register() { return "register"; }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(UserDto user) {
        try {
            userService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException e) {
            System.out.println(user.toString());
            userService.registerUser(user);

            return "redirect:/login";
        }
        System.out.println("User already exists");
        return "redirect:/register";
    }
}
