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
}
