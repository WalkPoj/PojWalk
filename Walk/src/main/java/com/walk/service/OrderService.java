package com.walk.service;

import com.walk.pojo.Mark;
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

    /**
     * 判断编辑资料的昵称是否存在
     * @param u_uickname
     * @return
     */
    public boolean selectUserExist(String u_uickname);

    /**
     * 新增商家入驻
     * @param mark
     * @return
     */
    public boolean insertMark(Mark mark,HttpSession session);
}
