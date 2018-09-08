package com.walk.service.impl;

import com.walk.dao.SceneryDao;
import com.walk.pojo.InsertScenry;
import com.walk.pojo.Scenery;
import com.walk.service.SceneryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SceneryServiceimpl implements SceneryService {

    @Autowired
    private SceneryDao sceneryDao;

    @Override
    public List<Scenery> findAllScenery(Scenery scenery, int page, int rows, String sort, String order) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("page", (page-1)*rows);
        param.put("rows", rows);
        param.put("sort", sort);
        param.put("order", order);
        param.put("m_id",scenery.getM_id());
        param.put("s_id", scenery.getS_id());
        param.put("s_title", scenery.getS_title());
        return sceneryDao.findByCondition(param);
    }

    @Override
    public long getSceneryCount(Scenery scenery) {
        return sceneryDao.findSceneryCount(scenery);
    }

    @Override
    public boolean insertScenry(InsertScenry insertScenry) {
        int orw = sceneryDao.insertScenry(insertScenry);
        if (orw>0)
            return true;
        return false;
    }

    @Override
    public int deleteScenry(List<Integer> ids) {
        return sceneryDao.deleteScenry(ids);
    }

    @Override
    public Scenery selectnoeScenery(int s_id) {
        return sceneryDao.selectnoeScenery(s_id);
    }
}
