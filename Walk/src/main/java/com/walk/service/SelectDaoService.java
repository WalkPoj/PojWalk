package com.walk.service;

import com.github.pagehelper.PageInfo;
import com.walk.pojo.*;
import org.springframework.ui.Model;

import java.util.List;

public interface SelectDaoService {
    //首页九条数据查询
    public List<Scenery> indexSelect();

    //分类查询
    public PageInfo<Scenery> listView(Model mod,int sPage,int ePage);

    //查询景区详情
    public Scenery selectDeal(int s_id);

    //查询线路id
    public int selectSeid(DataPiao dataPiao);

    //查询汽车
    public List<Carclass> selectCar(DataPiao dataPiao);
}
