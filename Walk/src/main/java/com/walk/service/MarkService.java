package com.walk.service;

import com.walk.pojo.Mark;

public interface MarkService {

    /**
     * 查询商家绑定的用户
     * @param u_id 用户id
     * @return
     */
    public Mark selectMark(int u_id);

    /**
     * 验证是否是商家手机
     * @param m_id
     * @return
     */
    public String selectPhone(int m_id);

    /**
     * 商家绑定的身份证查询
     * @param m_id
     * @return
     */
    public  String selectIdcard(int m_id);

    /**
     * 修改商家手机号
     * @param m_phone
     * @param m_id
     * @return
     */
    public int updatePhone(String m_phone,int m_id);
}
