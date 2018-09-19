package com.walk.util;

import com.walk.pojo.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class ApplicationListener implements HttpSessionListener, HttpSessionAttributeListener,ServletContextListener {

    public ApplicationListener() {
        System.out.println("进入监听器。。。");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("添加" + se.getName());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("删除" + se.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session创建************");
//        List<String> list = (List<String>) se.getSession().getServletContext().getAttribute("list");
//        System.out.println(""+list.get(0));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        List<String> list =(List<String>) se.getSession().getServletContext().getAttribute("list");
        User us =(User)se.getSession().getAttribute("user");
        if (list!=null){
        System.out.println(list.size()+"scsc"+us.getU_nickname());
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(""+list.get(i));
            if (us.getU_nickname().equals(list.get(i))){
                list.remove(list.get(i));
            }
         }
        }
        se.getSession().invalidate();//清除 session 中的所有信息
        System.out.println("session要销毁了");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext();
        List<String> list = new ArrayList<>();
        list.add("李白");
        servletContextEvent.getServletContext().setAttribute("list",list);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("孤男");
    }
}
