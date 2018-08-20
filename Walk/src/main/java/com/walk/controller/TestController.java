package com.walk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(Model mod){
        mod.addAttribute("index","a");
        System.out.println("aaa");
        return "model";
    }
}
