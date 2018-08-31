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
}
