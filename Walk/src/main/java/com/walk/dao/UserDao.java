package com.walk.dao;

import com.walk.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 商家登录验证
     * @param u_nickname
     * @param u_nickname
     * @return
     */
    public User selectUser(@Param("u_nickname") String u_nickname,@Param("u_password") String u_password);

    /**
     * 登录验证是否是商家
     * @param u_nickname
     * @param u_password
     * @return
     */
    public int isRoot(@Param("u_nickname") String u_nickname,@Param("u_password") String u_password);

    /**
     * 前台普通用户登录
     * @param user
     * @return
     */
    public User OrdinaryLogin(User user);

    /**
     * 前台注册判断手机号是否存在
     * @param u_phone
     * @return
     */
    public int PhoneExists(@Param("u_phone") String u_phone);
}
