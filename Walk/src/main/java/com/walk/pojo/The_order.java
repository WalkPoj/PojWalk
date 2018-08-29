package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class The_order {
    //订单id
    private int o_id;
    //购票用户
    private int u_id;
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
}
