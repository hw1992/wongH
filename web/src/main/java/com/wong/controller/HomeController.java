package com.wong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping(value="index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
}