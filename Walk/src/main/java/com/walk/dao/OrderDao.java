package com.walk.dao;

import com.walk.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    /**
     * 根据u_id查询我的订单
     * @param u_id
     * @return
     */
    public List<Map<String,Object>> selectOrder(@Param("u_id") int u_id,@Param("o_id") String o_id);

    /**
     * 编辑个人中心基本资料
     * @param user
     * @return
     */
    public int updateOrder(User user);

    /**
     * 查询个人中心基本信息
     * @param u_id
     * @return
     */
    public User selectUserOrder(@Param("u_id") int u_id);

    /**
     * 判断编辑资料的昵称是否存在
     * @param u_uickname
     * @return
     */
    public int selectUserExist(@Param("u_uickname") String u_uickname);
}
