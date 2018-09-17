package com.walk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.walk.dao.SelectDao;
import com.walk.pojo.*;
import com.walk.service.SelectDaoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SelectDaoServiceimpl implements SelectDaoService {
    @Autowired
    private SelectDao sdao;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    @Cacheable(value="indexScenery", key="'data_index'", unless="#result==null")
    public List<Scenery> indexSelect() {
        System.out.println("加载首页，首次访问数据库或数据已被销毁...");
        return sdao.indexSelect();
    }

    @Override
    @Cacheable(value="lvScenery", key="'data_page_'+#sPage", unless="#result==null")
    public PageInfo<Scenery> listView(Model mod,int sPage,int ePage) {
        System.out.println("加载所有景区，首次进入或数据已被销毁...");
        PageHelper.startPage(sPage,ePage);
        List<Scenery> sceneryList=sdao.listView();
        PageInfo<Scenery> pageInfo = new PageInfo<>(sceneryList);
        mod.addAttribute("scenery",sceneryList);
        return pageInfo;
    }
    @Override
    public Scenery selectDeal(int s_id) {
        return sdao.selectDeal(s_id);
    }

    @Override
    public int selectSeid(DataPiao dataPiao) {
        return sdao.selectSeid(dataPiao);
    }
    @Override
    public List<Carclass> selectCar(DataPiao dataPiao) {
        return sdao.selectCar(dataPiao);
    }

    @Override
    public List<Trainclass> selectTrain(DataPiao dataPiao) {
        return sdao.selectTrain(dataPiao);
    }

    @Override
    public List<Planeclass> selectPlane(DataPiao dataPiao) {
        return sdao.selectPlane(dataPiao);
    }

    @Override
    public int SaveOrder(The_order to) {
        return sdao.SaveOrder(to);
    }

    @Override
    public int Saveperson(Person per) {
        return sdao.Saveperson(per);
    }

    @Override
    public List<Scenery> selectByMod(int s_city,int s_price,int s_num) {
        return sdao.selectByMod(s_city,s_price,s_num);
    }

    @Override
    public String selectCname(int c_id) {
        return sdao.selectCname(c_id);
    }
}