package com.walk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("center")
public class GuangController {

    @RequestMapping("/Guang")
    public String Guang(int uid,int mid,HttpServletRequest request){
        request.setAttribute("uid",uid);
        request.setAttribute("mid",mid);
        System.out.println(uid);
        System.out.println(mid);
        return "center/Guang";
    }

}
