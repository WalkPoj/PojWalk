package com.walk.controller;

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

    @RequestMapping("mp")
    public String Showimg(Model mod){
        List<Scenery> s = sdaos.indexSelect();
        mod.addAttribute("scenery",s);
        return "cpts_398_pn/deals";
    }
}
