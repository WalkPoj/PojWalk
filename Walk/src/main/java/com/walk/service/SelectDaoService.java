package com.walk.service;

import com.walk.pojo.Scenery;

import java.util.List;

public interface SelectDaoService {
    //首页九条数据查询
    public List<Scenery> indexSelect();

    //分类查询
    public List<Scenery> listView(int s_num,int price,String address,int sPage,int ePage);
}