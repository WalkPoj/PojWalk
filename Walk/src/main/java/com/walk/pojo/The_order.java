package com.walk.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class
The_order {
    //订单id
    private String o_id;
    //购票用户
    private int u_id;
    //联系电话
    private String u_phone;
    //商家id
    private int m_id;
    //景区id
    private int s_id;
    //人数
    private int o_person;
    //控制出行，0汽车，1火车，2飞机
    private int tools_id;
    //车次id
    private String class_id;
    //费用，合计
    private double o_price;
    //下单时间
    private Date o_create;
}
