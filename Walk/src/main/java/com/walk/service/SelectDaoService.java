package com.walk.service;

import com.github.pagehelper.PageInfo;
import com.walk.pojo.*;
import org.apache.ibatis.annotations.Param;
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

    //查火车
    public List<Trainclass> selectTrain(DataPiao dataPiao);

    //查飞机
    public List<Planeclass> selectPlane(DataPiao dataPiao);

    //新增订单
    public int SaveOrder(The_order to);

    //新增旅客
    public int Saveperson(Person per);l

    //筛选
    public List<Scenery> selectByMod(@Param("s_city")int s_city, @Param("s_price")int s_price, @Param("s_num")int s_num);
}
