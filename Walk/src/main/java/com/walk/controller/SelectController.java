package com.walk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.walk.pojo.Scenery;
import com.walk.service.SelectDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SelectController {
    @Autowired
    public SelectDaoService sdaos;

    /**
     * 查询首页推荐
     * @param mod
     * @return
     */
    @RequestMapping("indexShow")
    public String Showimg(Model mod){
        List<Scenery> s = sdaos.indexSelect();
        mod.addAttribute("scenery",s);
        return "cpts_398_pn/deals";
    }

    @RequestMapping("listView")
    public String ListView(Model mod,int s_num,String price,String address,int sPage,int ePage){
        List<Scenery> s = sdaos.listView(s_num,price,address,sPage,ePage);
        PageInfo<Scenery> pageInfo = new PageInfo<>(s);
        //总页数
        mod.addAttribute("pageNum",pageInfo.getPages());
        //当前页数
        mod.addAttribute("pageNext",pageInfo.getPageNum());
        mod.addAttribute("scenery",s);
        return "cpts_398_pn/products";
    }

}
