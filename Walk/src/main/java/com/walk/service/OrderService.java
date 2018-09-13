package com.walk.service;

import com.walk.pojo.User;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 根据u_id查询我的订单
     * @param u_id
     * @return
     */
    public List<Map<String,Object>> selectOrder(int u_id,String o_id);

    /**
     * 编辑个人中心基本资料
     * @param user
     * @return
     */
    public boolean updateOrder(User user);

    /**
     * 查询个人中心基本信息
     * @param u_id
     * @return
     */
    public User selectUserOrder(int u_id);
}
