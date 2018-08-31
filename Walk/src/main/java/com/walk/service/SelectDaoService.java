package com.walk.service;

import com.github.pagehelper.PageInfo;
import com.walk.pojo.Scenery;
import org.springframework.ui.Model;

import java.util.List;

public interface SelectDaoService {
    //首页九条数据查询
    public List<Scenery> indexSelect();

    //分类查询
    public PageInfo<Scenery> listView(Model mod,int sPage,int ePage);
}
