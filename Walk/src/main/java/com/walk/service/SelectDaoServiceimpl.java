package com.walk.service;

import com.walk.dao.SelectDao;
import com.walk.pojo.Scenery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SelectDaoServiceimpl implements SelectDaoService {
    @Autowired
    private SelectDao sdao;

    @Override
    public List<Scenery> indexSelect() {
        return sdao.indexSelect();
    }
}
