package com.walk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.walk.dao.SelectDao;
import com.walk.pojo.Scenery;
import com.walk.service.SelectDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Transactional
public class SelectDaoServiceimpl implements SelectDaoService {
    @Autowired
    private SelectDao sdao;

    @Override
    @Cacheable(value="indexScenery", key="'data_index'", unless="#result==null")
    public List<Scenery> indexSelect() {
        System.out.println("首次访问数据库，加载首页");
        return sdao.indexSelect();
    }

    @Override
    @Cacheable(value="lvScenery", key="'data_page_'+#sPage", unless="#result==null")
    public PageInfo<Scenery> listView(Model mod,int sPage,int ePage) {
        System.out.println("首次访问数据库，正在加载第"+sPage+"页");
        PageHelper.startPage(sPage,ePage);
        List<Scenery> sceneryList=sdao.listView();
        PageInfo<Scenery> pageInfo = new PageInfo<>(sceneryList);
        mod.addAttribute("scenery",sceneryList);
        return pageInfo;
    }
}