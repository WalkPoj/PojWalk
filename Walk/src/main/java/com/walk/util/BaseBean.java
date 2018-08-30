package com.walk.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseBean implements Serializable {
    //==============分页字段开始================
    private Integer pageNum;//当前页
    private Integer pageSize;//当前页数量
    private String orderBy;
    private Integer pn = Integer.valueOf(1);
    private Integer ps = Integer.valueOf(10);
    //==============分页字段结束================
}
