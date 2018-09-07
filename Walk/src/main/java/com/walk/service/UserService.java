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

    /**
     * 修改密码验证（查询原密码）
     * @param uid
     * @return
     */
    public String ispsw(int uid);

    /**
     * 密码修改
     * @param u_password
     * @param u_id
     * @return
     */
    public  boolean updatepsw(String u_password,int u_id);
}
