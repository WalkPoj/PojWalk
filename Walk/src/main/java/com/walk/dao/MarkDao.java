package com.walk.dao;

import com.walk.pojo.Mark;
import org.apache.ibatis.annotations.Param;

/**
 * 商家接口
 */
public interface MarkDao {
    /**
     * 查询商家绑定的用户
     * @param u_id 用户id
     * @return
     */
    public Mark selectMark(@Param("u_id")int u_id);

    /**
     * 验证是否是商家手机
     * @param m_id
     * @return
     */
    public String selectPhone(@Param("m_id")int m_id);

    /**
     * 商家绑定的身份证查询
     * @param m_id
     * @return
     */
    public  String selectIdcard(@Param("m_id")int m_id);

    /**
     * 修改商家手机号
     * @param m_phone
     * @param m_id
     * @return
     */
    public int updatePhone(@Param("m_phone")String m_phone,@Param("m_id")int m_id);
}
