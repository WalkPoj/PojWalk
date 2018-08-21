package com.walkBAM.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ApplicationListener implements HttpSessionListener, HttpSessionAttributeListener {

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
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session要销毁了");
    }
}
