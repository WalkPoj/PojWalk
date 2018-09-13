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
     * 根据u_id查询我的订单
     * @param u_id
     * @param o_id
     * @return
     */
    @Override
    public  List<Map<String,Object>> selectOrder(int u_id,String o_id) {
        return orderDao.selectOrder(u_id,o_id);
    }

    /**
     * 编辑个人中心基本资料
     * @param user
     * @return
     */
    @Override
    public boolean updateOrder(User user) {
        if(orderDao.updateOrder(user)>0)
            return true;
        return false;
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
