package com.walk.service.impl;

import com.walk.dao.The_orderDao;
import com.walk.pojo.Echarts;
import com.walk.service.MorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MorderServiceimpl implements MorderService {

    @Autowired
    private The_orderDao tdao;

    @Override
    public List<Map<String, Object>> selectOrder(Map<String,Object> map) {
        return this.tdao.selectOrder(map);
    }

    @Override
    public long findOrderCount(Map<String,Object> map) {

        return this.tdao.findOrderCount(map);
    }

    @Override
    public List<Echarts> selectEcharts(int m_id) {
        return this.tdao.selectEcharts(m_id);
    }

    @Override
    public List<Echarts> selectMonth(int m_id,String month) {
        return tdao.selectMonth(m_id,month);
    }

    @Override
    public List<Echarts> selectprice(int m_id) {
        return tdao.selectprice(m_id);
    }
}
