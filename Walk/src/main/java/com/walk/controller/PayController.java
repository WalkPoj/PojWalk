package com.walk.controller;

import com.walk.pojo.Data_Class_INFO;
import com.walk.pojo.Order_info;
import com.walk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class PayController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("Pay")
    public String Pay(Order_info oi, HttpSession session){
        //获取用户并加入redis
        User u = (User)session.getAttribute("user");
        redisTemplate.opsForValue().set("pay_user_"+u.getU_id(),u);
        redisTemplate.expire("pay_user_"+u.getU_id(),600,TimeUnit.SECONDS); //十分钟失效
        //获取车票信息并加入redis
        Data_Class_INFO dci=(Data_Class_INFO) session.getAttribute("Data_INFO");
        redisTemplate.opsForValue().set("pay_dci_"+u.getU_id(),dci);
        redisTemplate.expire("pay_dci_"+u.getU_id(),600,TimeUnit.SECONDS); //十分钟失效
        //获取订单信息并加入redis
        redisTemplate.opsForValue().set("pay_oi_"+u.getU_id(),oi);
        redisTemplate.expire("pay_oi_"+u.getU_id(),600,TimeUnit.SECONDS); //十分钟失效
        /**
         * 接收参数 创建订单
         */
        String token = "PjmVxipkfoBblTBEOwwlFVu4mpn594cH"; //记得更改 http://codepay.fateqq.com 后台可设置
        String codepay_id ="92513" ;//记得更改 http://codepay.fateqq.com 后台可获得

        String price="0.1"; //表单提交的价格
        String type="1"; //支付类型  1：支付宝 2：QQ钱包 3：微信
        String pay_id=String.valueOf(u.getU_id()); //支付人的唯一标识
        String param=oi.getParam(); //自定义一些参数 支付后返回

        String notify_url="http://walk.nieanshow.cn";//通知地址
        String return_url="http://localhost:8080/MX/SaveOrder?uid="+u.getU_id();//支付后同步跳转地址
        if(price==null){
            price="1";
        }
        //参数有中文则需要URL编码
        String url="http://codepay.fateqq.com:52888/creat_order?id="+codepay_id+"&pay_id="+pay_id+"&price="+price+"&type="+type+"&token="+token+"&param="+param+"&notify_url="+notify_url+"&return_url="+return_url;
        return "redirect:"+url;
    }
}
