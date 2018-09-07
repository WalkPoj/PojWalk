package com.walk.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 新增景点数据传输
 */
public class InsertScenry {
    private  int m_id;
    private String s_title;
    private String s_txt;
    private String s_img;
    private String s_fmImg;
    private String s_price;
    private String s_price_yuan;
}
