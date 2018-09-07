package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Planeclass {
    //班次id，固定班次
    private String pc_id;
    //线路id，外键
    private int se_id;
    //起飞时间
    private Date pc_send;
    //降落时间
    private Date pc_end;
    //飞机类型，外键
    private int p_typeid;
    //机票价格，固定线路固定价格
    private double pc_price;
    //飞机类型
    private String p_type;
}
