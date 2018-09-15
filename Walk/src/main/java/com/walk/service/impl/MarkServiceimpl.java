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

    @Override
    public String selectPhone(int m_id) {
        return mdao.selectPhone(m_id);
    }

    @Override
    public String selectIdcard(int m_id) {
        return mdao.selectIdcard(m_id);
    }

    @Override
    public int updatePhone(String m_phone, int m_id) {
        return mdao.updatePhone(m_phone,m_id);
    }
}
