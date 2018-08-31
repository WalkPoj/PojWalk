package com.walk.service;

import com.walk.pojo.Mark;

public interface MarkService {

    /**
     * 查询商家绑定的用户
     * @param u_id 用户id
     * @return
     */
    public Mark selectMark(int u_id);
}
