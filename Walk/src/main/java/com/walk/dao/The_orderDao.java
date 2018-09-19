package com.walk.dao;


import com.walk.pojo.Echarts;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单接口
 */
public interface The_orderDao {

    /**
     * 订单查询
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
    public List<Echarts> selectEcharts(@Param("m_id") int m_id);

    /**
     * 查询某个月份的销量
     * @param m_id
     * @param month
     * @return
     */
    public List<Echarts> selectMonth(@Param("m_id") int m_id,@Param("month")String month);

    /**
     * 销售额
     * @param m_id
     * @return
     */
    public List<Echarts> selectprice(@Param("m_id") int m_id);
}
