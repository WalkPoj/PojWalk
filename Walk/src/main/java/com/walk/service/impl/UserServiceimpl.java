package com.walk.service.impl;

import com.walk.dao.UserDao;
import com.walk.pojo.User;
import com.walk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserDao udao;

    @Override
    public User login(User user) {
        return udao.selectUser(user.getU_nickname(),user.getU_password());
    }

    @Override
    public int isRoot(User user) {
        return udao.isRoot(user.getU_nickname(),user.getU_password());
    }

    /**
     * 前台普通用户登录
     * @param user
     * @return
     */
    @Override
    public User OrdinaryLogin(User user) {
        if((user=udao.OrdinaryLogin(user))!=null)
            return user;
        return null;
    }

    /**
     * 前台注册判断手机号是否存在
     * @param u_phone
     * @return
     */
    @Override
    public boolean PhoneExists(String u_phone) {
        if(udao.PhoneExists(u_phone)>0)
            return true;
        return false;
    }
}
