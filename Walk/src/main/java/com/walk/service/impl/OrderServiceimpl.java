package com.walk.service.impl;

import com.walk.dao.OrderDao;
import com.walk.pojo.User;
import com.walk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceimpl  implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 查询个人中心我的订单
     * @param u_id
     * @return
     */
    @Override
    public List<Map<String, Object>> selectOrder(int u_id) {
        return orderDao.selectOrder(u_id);
    }

    /**
     * 查询个人中心基本信息
     * @param u_id
     * @return
     */
    @Override
    public User selectUserOrder(int u_id) {
        return orderDao.selectUserOrder(u_id);
    }
}
