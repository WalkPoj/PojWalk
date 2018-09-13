package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Scenery implements Serializable {
    //景区id
    private int s_id;
    //
    private int s_city;
    //景区评分
    private double s_num;
    //景区负责商家
    private int m_id;
    //景区标题
    private String s_title;
    //景区详情
    private String s_txt;
    //景区图片路径
    private String s_img;
    //景区封面
    private String s_fmImg;
    //景区点击量
    private int s_click;
    //景区价格
    private double s_price;
    //景区原价
    private double s_price_yuan;
    //景区认证状态
    private int s_state;
}
