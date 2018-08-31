package com.walk.service.impl;

import com.walk.dao.MarkDao;
import com.walk.pojo.Mark;
import com.walk.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceimpl implements MarkService {

    @Autowired
    private MarkDao mdao;

    @Override
    public Mark selectMark(int u_id) {
        return mdao.selectMark(u_id);
    }
}
