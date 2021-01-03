package com.oauth2.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping("/auth/login")
    public ModelAndView loginPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("loginProcessUrl","/auth/form");
        modelAndView.setViewName("base-login");
        return modelAndView;
    }

    @GetMapping("/auth/login2")
    public ModelAndView loginPage3(Model model){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("loginProcessUrl","/auth/form2");
        modelAndView.setViewName("base-login2");
        return modelAndView;
    }

    @GetMapping("/hello")
    public ModelAndView hello(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }

}
