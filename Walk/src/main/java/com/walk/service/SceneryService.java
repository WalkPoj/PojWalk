package com.walk.service;

import com.walk.pojo.InsertScenry;
import com.walk.pojo.Scenery;

import java.util.List;

public interface SceneryService {

    /**
     * 景点查询
     * @param scenery
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    public List<Scenery> findAllScenery(Scenery scenery, int page, int rows, String sort, String order);

    /**
     * 查询总数据量
     * @param scenery
     * @return
     */
    public long getSceneryCount(Scenery scenery);

    /**
     * 新增景点
     * @param insertScenry
     * @return
     */
    public  boolean insertScenry(InsertScenry insertScenry);

    /**
     * 删除景点
     * @param ids
     * @return
     */
    public int deleteScenry(List<Integer> ids);
}
