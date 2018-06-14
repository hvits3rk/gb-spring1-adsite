package com.romantupikov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {

    @ModelAttribute("activeController")
    public String activeController() {
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

}
