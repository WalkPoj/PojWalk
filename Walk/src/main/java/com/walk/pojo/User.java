package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    //用户id
    private int u_id;
    //用户名，真实姓名
    private String u_name;
    //昵称，网名，默认walk_手机号
    private String u_nickname;
    //密码
    private String u_password;
    //标识用户，0普通用户，1管理员
    private int u_root;
    //用户手机号
    private String u_phone;
    //身份证号
    private String u_idcard;
    //标识用户认证
    private int cert;
}
