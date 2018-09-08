package com.walk.dao;


import com.walk.pojo.InsertScenry;
import com.walk.pojo.Scenery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SceneryDao {

    /**
     * 景点管理查询
     * @param param
     * @return
     */
    public List<Scenery> findByCondition(Map<String,Object> param);

    /**
     * 求总数据量
     * @return
     */
    public long findSceneryCount(Scenery scenery);

    /**
     * 新增景点
     * @param insertScenry
     * @return
     */
    public  int insertScenry(InsertScenry insertScenry);

    /**
     * 删除景点
     * @param ids
     * @return
     */
    public int deleteScenry(List<Integer> ids);

    /**
     * 查询单个景点
     * @param s_id
     * @return
     */
    public  Scenery selectnoeScenery(@Param("s_id") int s_id);
}
