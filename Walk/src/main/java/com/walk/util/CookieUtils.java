package com.walk.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static String getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void writeCookie(HttpServletResponse response, String cookieName, String value){
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }

    public static void saveCookie(String username, HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        for (Cookie c : cookie) {
            if ("userMe".equals(c.getName())) {
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        // 实例化一个Cookie
        Cookie cookie1 = new Cookie("userMe", username);
        // 设置Cookie的生命期限10分钟
        cookie1.setMaxAge(1800);
        // 添加Cookie到客户端
        response.addCookie(cookie1);

    }
    public static void removeCookie(HttpServletRequest request,HttpServletResponse response, String cookieName){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName)){
                cookie.setValue(null);
                cookie.setMaxAge(0);// 立即销毁cookie
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
    }
}
