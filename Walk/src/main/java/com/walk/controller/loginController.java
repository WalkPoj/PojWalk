package com.walk.controller;

import com.walk.pojo.Mark;
import com.walk.pojo.User;
import com.walk.service.CpachaService;
import com.walk.service.MarkService;
import com.walk.service.UserService;
import com.walk.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class loginController {

    @Autowired
    private CpachaService cve;

    @Autowired
    private UserService uve;

    @Autowired
    private MarkService mve;

    @RequestMapping("/indexss")
    public ModelAndView login(ModelAndView mav){
        System.out.println("进入");
        mav.setViewName("redirect:center/login.html");
       return mav;
    }

    @RequestMapping("/CpachaServlet")
    public void CpachaServlet(String method,HttpSession session ,HttpServletResponse response )throws IOException {
        if ("GetVCode".equals(method)) {
            System.out.println("进入验证码生成器");
            cve.generateLoginCpacha(session,response);
            System.out.println("结束");
        }
    }

    @PostMapping("/LoginServlet")
    @ResponseBody
    public String LoginServlet(ModelAndView mav, User user,String vcode,String method,HttpServletRequest request){
        if("logout".equals(method)){
            logout(mav, request);
            return null;
        }
        String loginCpacha = request.getSession().getAttribute("loginCapcha").toString();
        if(StringUtil.isEmpty(vcode)){
            return "vcodeError";
        }
        if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
            return "vcodeError";
        }
        //验证码验证通过，对比用户名密码是否正确
        String loginStatus = "loginFaild";
        System.out.println(user.getU_nickname());
        System.out.println(user.getU_password());
        User us = uve.login(user);
        if(us == null){
            return "loginError";
        }
        if (uve.isRoot(user)!=1){
            return "NoIsMerchants";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", us);
        session.setAttribute("userType", us.getU_root());
        loginStatus = "loginSuccess";
        System.out.println(loginStatus);
        return loginStatus;
    }

    private ModelAndView logout(ModelAndView mav, HttpServletRequest request){
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userType");
        mav.setViewName("login.html");
        return mav;
    }

    @RequestMapping("SystemServlet")
    public String SystemServlet( ModelAndView mav ,HttpSession session){
        System.out.println("经过我同意没");
        User user = (User) session.getAttribute("user");
        System.out.println("要查询的商家id"+user.getU_id());
        Mark mark = mve.selectMark(user.getU_id());
        session.setAttribute("mark",mark);
        System.out.println(mark.getM_name());
        return "center/index";
    }

}
