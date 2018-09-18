package com.walk.service.impl;

import com.walk.dao.OrderDao;
import com.walk.pojo.User;
import com.walk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceimpl  implements OrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 根据u_id查询我的订单
     * @param o_id
     * @return
     */
    @Override
    public  List<Map<String,Object>> selectOrder(String o_id,HttpSession session) {
        User u=(User) session.getAttribute("user");
        return orderDao.selectOrder(u.getU_id(),o_id);
    }

    /**
     * 编辑个人中心基本资料
     * @param user
     * @return
     */
    @Override
    public int updateOrder(User user,HttpSession session) {
        User u=(User)session.getAttribute("user");
        user.setU_id(u.getU_id());
        if(orderDao.updateOrder(user)>0)
            return 1;
        return 0;
    }

    /**
     * 查询个人中心基本信息
     * @return
     */
    @Override
    public User selectUserOrder(HttpSession session) {
        User u=(User)session.getAttribute("user");
        return orderDao.selectUserOrder(u.getU_id());
    }

    /**
     * 判断编辑资料的昵称是否存在
     * @param u_uickname
     * @return
     */
    @Override
    public boolean selectUserExist(String u_uickname){
        if(orderDao.selectUserExist(u_uickname)>0)
            return true;
        return false;
    }

}
