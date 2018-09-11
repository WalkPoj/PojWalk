package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.fileupload.util.LimitedInputStream;

import java.util.List;

/**
 * 订单客户信息
 */
@Getter
@Setter
public class Order_info {
    private String u_name;
    private String u_phone;
    private String u_email;
    private List<String> u_lv_name;
    private List<String> u_lv_cardid;
    private List<String> u_lv_phone;
}
