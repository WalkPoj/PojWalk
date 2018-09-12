package com.walk.service;

import com.walk.pojo.Echarts;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MorderService {
    /**
     * 商家查询订单
     * @return
     */
    public List<Map<String,Object>> selectOrder(Map<String,Object> map);

    /**
     * 求总数据量
     * @return
     */
    public long findOrderCount(Map<String,Object> map);

    /**
     * 柱状图
     * @return
     */
    public List<Echarts> selectEcharts(int m_id);
}
