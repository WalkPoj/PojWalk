package com.walk.dao;

import com.walk.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    /**
     * 查询个人中心我的订单
     * @param u_id
     * @return
     */
    public List<Map<String,Object>> selectOrder(@Param("u_id") int u_id);

    /**
     * 查询个人中心基本信息
     * @param u_id
     * @return
     */
    public User selectUserOrder(@Param("u_id") int u_id);
}
