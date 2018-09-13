package com.walk.controller;

import com.walk.pojo.User;
import com.walk.service.OrderService;
import com.walk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginsController {

    @Autowired
    private UserService uve;

    @Autowired
    private OrderService ove;

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
        if(users!=null&&users.getU_root()==1){
            if(name.equals(users.getU_nickname())||name.equals(users.getU_phone())){
                session.setAttribute("user",users);
                return "index";
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

    /**
     * 个人中心操作
     * @param xz
     * @param o_id
     * @param mod
     * @param session
     * @return
     */
    @RequestMapping("/Order.action")
    public String Order(String xz,String o_id,Model mod,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Map<String,Object>> order=null;
        if(o_id!=null){
            //查看详情订单
            order=ove.selectOrder(0,o_id);
        }else{
            //我的订单信息
            order=ove.selectOrder(user.getU_id(),null);
        }
        //查询个人中心基本信息
        User us=ove.selectUserOrder(user.getU_id());
        mod.addAttribute("xz",xz);
        mod.addAttribute("order",order);
        mod.addAttribute("us",us);
        return "center_index/index";
    }

    @RequestMapping("/updateOrder.action")
    public String updateOrder(User user,String o_id){
        if(ove.updateOrder(user)){
            System.out.println("1"+user.getU_id()+"修改成功");
        }else{
            System.out.println("2"+user.getU_id()+"修改失败");
        }
        return "redirect:/Order.action?xz=1&u_id="+user.getU_id()+"&o_id="+o_id;
    }
}
