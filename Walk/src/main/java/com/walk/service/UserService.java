package com.walk.service;

import com.walk.pojo.User;

public interface UserService {
    /**
     * 用户登录验证
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 验证是否是商家
     * @param user
     * @return
     */
    public int isRoot(User user);
}
