package com.walk.controller;

import com.walk.pojo.User;
import com.walk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginsController {

    @Autowired
    private UserService uve;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("/indexLogin.action")
    public String indexLogin(){
        return "login/index";
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }

    /**
     * 前台普通用户登录
     * @param user
     * @param name
     * @param session
     * @return
     */
    @RequestMapping("/login.action")
    public String OrdinaryLogin(User user, @RequestParam("name") String name, HttpSession session){
        if (name.length()==11){
            user.setU_nickname(name);
            user.setU_phone(name);
        }else{
            user.setU_nickname(name);
            user.setU_phone(null);
        }
        User users=uve.OrdinaryLogin(user);
        if(users!=null&&users.getU_root()==0){
            if(name.equals(users.getU_nickname())||name.equals(users.getU_phone())){
                session.setAttribute("user",users);
                return "index.html";
            }else{
                return "login/index";
            }
        }else{
            return "login/index";
        }
    }

    /**
     * 前台注册判断手机号是否存在
     * @param u_phone
     * @param response
     * @return
     */
    @RequestMapping("/PhoneExists")
    @ResponseBody
    public boolean PhoneExists(String u_phone, HttpServletResponse response){
        response.setContentType("text/html;charset=GBK");
        return uve.PhoneExists(u_phone);
    }
}
