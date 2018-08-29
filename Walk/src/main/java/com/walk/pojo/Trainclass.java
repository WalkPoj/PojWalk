package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Trainclass {
    //火车班次id
    private String tc_id;
    //线路id
    private int se_id;
    //火车发车时间
    private Date tc_send;
    //火车到站时间
    private Date tc_end;
    //火车类型
    private int t_typeid;
    //火车票价
    private double tc_price;
}
