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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class loginController {

    @Autowired
    private CpachaService cve;

    @Autowired
    private UserService uve;

    @Autowired
    private MarkService mve;

    @RequestMapping("/indexss")
    public String login(ModelAndView mav,HttpServletRequest request){
        System.out.println("进入");
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            ServletContext application = request.getSession().getServletContext();
            List<String> list = (List<String>) application.getAttribute("list");
            for (Cookie c: cookies) {
                if ("loginname".equals(c.getName())){
                    for (String name:list ) {
                        System.out.println(c.getValue()+""+name);
                        if (c.getValue().equals(name)){
                            request.setAttribute("repeat",1);
                            return "center/login.html";
                        }
                    }
                    //System.out.println(c.getValue()+""+c.getName());
                    int u_id = uve.selectuid(c.getValue());
                    User us = new User();
                    us.setU_nickname(c.getValue());
                    us.setU_id(u_id);
                    request.getSession().setAttribute("user",us);
                    return "forward:/SystemServlet";
                }
            }
        }

        request.setAttribute("repeat",0);
       return "center/login.html";
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
    public String LoginServlet(ModelAndView mav, User user,String vcode,String method,HttpServletRequest request,HttpServletResponse response){
        if("logout".equals(method)){
            logout(mav, request);
            return null;
        }
        String loginCpacha = request.getSession().getAttribute("loginCapcha").toString();
        ServletContext application = request.getSession().getServletContext();
        List<String> list = (List<String>) application.getAttribute("list");

        if(StringUtil.isEmpty(vcode)){
            return "vcodeError";
        }
        if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
            return "vcodeError";
        }
        //验证码验证通过，对比用户名密码是否正确
        String loginStatus = "loginFaild";
        User us = uve.login(user);
        if(us == null){
            return "loginError";
        }
        if (uve.isRoot(user)!=2){
            return "NoIsMerchants";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", us);
        session.setAttribute("userType", us.getU_root());
        for (String name:list ) {
            if (us.getU_nickname().equals(name)){
                return "repeat";
            }
        }
        list.add(us.getU_nickname());
        Cookie cookieName=new Cookie("loginname", us.getU_nickname());
       // Cookie cookiePassword=new Cookie("loginpassword", us.getU_password());
        cookieName.setPath(request.getContextPath());
        //cookiePassword.setPath(request.getContextPath());
        cookieName.setMaxAge(60*60*3);//只保存一分钟
        //cookiePassword.setMaxAge(60*60*3);//只保存一分钟
        response.addCookie(cookieName);
       // response.addCookie(cookiePassword);
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
        User user = (User) session.getAttribute("user");
        System.out.println("要查询的商家id"+user.getU_id());
        Mark mark = mve.selectMark(user.getU_id());
        session.setAttribute("mark",mark);
        System.out.println(mark.getM_name());
        return "center/index";
    }

}
