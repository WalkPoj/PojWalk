package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data_Order {
    private int s_id;
    private int m_id;
    private int se_id;
    private String se_start;
    private String se_end;
    private int person_num;
    private double price_order;
    private String text;

}
