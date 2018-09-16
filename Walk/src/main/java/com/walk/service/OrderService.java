package com.walk.service;

import com.walk.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 根据u_id查询我的订单
     * @param
     * @return
     */
    public List<Map<String,Object>> selectOrder(String o_id,HttpSession session);

    /**
     * 编辑个人中心基本资料
     * @param user
     * @return
     */
    public int updateOrder(User user,HttpSession session);

    /**
     * 查询个人中心基本信息
     * @return
     */
    public User selectUserOrder(HttpSession session);

}
