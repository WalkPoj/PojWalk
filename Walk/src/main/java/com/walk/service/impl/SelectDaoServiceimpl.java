package com.walk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.walk.dao.SelectDao;
import com.walk.pojo.Scenery;
import com.walk.service.SelectDaoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Scenery> indexSelect() {
        return sdao.indexSelect();
    }

    @Override
    public PageInfo<Scenery> listView(Model mod,int sPage,int ePage) {
        PageHelper.startPage(sPage,ePage);
        List<Scenery> s=sdao.listView();
        PageInfo<Scenery> pageInfo = new PageInfo<>(s);
        mod.addAttribute("scenery",s);
        return pageInfo;
    }
}
