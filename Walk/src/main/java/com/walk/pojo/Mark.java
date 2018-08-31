package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mark {
    //商家id，主键
    private int m_id;
    //商家名称
    private String m_name;
    //商家地址
    private String m_address;
    //商家联系方式
    private  String m_phone;
    //商家负责人
    private int u_id;
    //商家认证状态
    private int m_state;
}
