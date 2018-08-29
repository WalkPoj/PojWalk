package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Carclass {
    //班次id，固定班次
    private String cc_id;
    //线路id，外键
    private int se_id;
    //发车时间
    private Date cc_send;
    //到站时间
    private Date cc_end;
    //汽车类型，外键
    private int c_typeid;
    //汽车价格，固定线路固定价格
    private double cc_price;
}
