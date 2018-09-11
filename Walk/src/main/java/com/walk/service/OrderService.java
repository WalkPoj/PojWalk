package com.walk.service;

import com.walk.pojo.User;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 查询个人中心我的订单
     * @param u_id
     * @return
     */
    public List<Map<String,Object>> selectOrder(int u_id);

    /**
     * 查询个人中心基本信息
     * @param u_id
     * @return
     */
    public User selectUserOrder(int u_id);
}
