package com.walk.controller;

import com.show.api.ShowApiRequest;
import com.walk.pojo.User;
import com.walk.service.OrderService;
import com.walk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginsController {

    @Autowired
    private UserService uve;

    @Autowired
    private OrderService ove;

    @Autowired
    private RedisTemplate redisTemplate;

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
    public String OrdinaryLogin(User user,@RequestParam("name") String name, HttpSession session){
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
     * 跳转个人中心
     * @return
     */
    @RequestMapping("/Order.html")
    public String Order(){
        return "center_index/indexs";
    }

    /**
     * 查询个人中心基本信息
     * @return
     */
    @RequestMapping("/Users")
    @ResponseBody
    public User user(HttpSession session){
        User users=ove.selectUserOrder(session);
        return users;
    }

    /**
     * 编辑个人中心基本资料
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/updateUsers")
    @ResponseBody
    public User update(User user,HttpSession session){
        System.out.println(user.getU_nickname());
        if(ove.updateOrder(user,session)>0)
            return user;
        return null;
    }

    /**
     * 查询我的订单
     * @param o_id
     * @param session
     * @return
     */
    @RequestMapping("/findByTheorder")
    @ResponseBody
    public List<Map<String,Object>>  findByTheorder(String o_id,HttpSession session){
        List<Map<String,Object>> list=new ArrayList<>();
        if(o_id==null){
            System.out.println("查询用户所有订单:");
            System.out.println(o_id);
            list=ove.selectOrder(o_id,session);
            for (Map<String, Object> stringObjectMap : list) {
                System.out.println(stringObjectMap.get("o_id"));
            }
            System.out.println(list.size());
        }else{
            System.out.println("查询一个订单的详情:");
            list=ove.selectOrder(o_id,session);
            for (Map<String, Object> stringObjectMap : list) {
                System.out.println("1:"+stringObjectMap.get("o_id"));
            }
            System.out.println("2:"+list.size());
        }
        return list;
    }

    /**
     * 注册
     * @param u
     * @param mod
     * @return
     */
    @PostMapping("SaveUser")
    public String addUser(User u,Model mod){
        System.out.println("开始验证并注册");
        String vali = (String)redisTemplate.opsForValue().get("u_phone_"+u.getU_phone());
        u.setU_nickname("walk_"+u.getU_phone());
        u.setU_password((String)redisTemplate.opsForValue().get("u_pwd_"+u.getU_phone()));
        if (u.getValidation().equals(vali)){
            int i = uve.addUser(u);
            mod.addAttribute("msg","注册成功");
            return "login/index";
        }else {
            mod.addAttribute("msg","验证码错误");
            return "login/index";
        }
    }

    /**
     * 发送验证
     */
    String appid = "71437";
    String secret = "a2e0ddb9fc5b4421ba305e9464b12462";
    @PostMapping("SaveNumbertoRides")
    @ResponseBody
    public String SaveNumbertoRides(String u_phone,int row){
        int random = (int)((Math.random()*9+1)*100000);
        String r = String.valueOf(random);
        String u_pwd = "walk"+r;
        redisTemplate.opsForValue().set("u_phone_"+u_phone,r);
        redisTemplate.opsForValue().set("u_pwd_"+u_phone,u_pwd);
        redisTemplate.expire("u_phone_"+u_phone,180,TimeUnit.SECONDS);
        String res = "";
        if (row == 0){
            res = (new ShowApiRequest("http://route.showapi.com/28-1", this.appid, this.secret)).addTextPara("mobile", ""+u_phone+"").addTextPara("content", "{ code:'"+r+"',minute:'3',name:'您的手机号："+u_phone+",您的密码为："+u_pwd+"，请在48小时修改密码！'}").addTextPara("tNum", "T170317002979").addTextPara("big_msg", "1").post();
        }else{
            res = (new ShowApiRequest("http://route.showapi.com/28-1", this.appid, this.secret)).addTextPara("mobile", ""+u_phone+"").addTextPara("content", "{ code:'"+r+"',minute:'3',name:'您正在修改密码'}").addTextPara("tNum", "T170317002979").addTextPara("big_msg", "1").post();
        }
        System.out.println(res);
        return r;
    }

    /**
     * 修改密码
     * @param pwd
     * @param session
     * @return
     */
    @PostMapping("ModifyPwd")
    @ResponseBody
    public boolean ModifyPwd(String pwd,HttpSession session){
        User u = (User)session.getAttribute("user");
        boolean res = uve.updatepsw(pwd,u.getU_id());
        return res;
    }

}
