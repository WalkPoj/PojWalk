package com.walk.dao;

import com.walk.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectDao {
    //首页九条数据查询
    public List<Scenery> indexSelect();

    //分类查询
    public List<Scenery> listView();

    //查询景区详情
    public Scenery selectDeal(@Param("s_id")int s_id);

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
    public int Saveperson(Person per);

    //筛选
    public List<Scenery> selectByMod(@Param("s_city")int s_city,@Param("s_price")int s_price,@Param("s_num")int s_num);

    public String selectCname(@Param("c_id")int c_id);



}

