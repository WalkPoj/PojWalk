package com.walk.dao;

import com.walk.pojo.City;

import java.util.List;

/**
 * 城市
 */
public interface CityDao {

    /**
     * 查询所有城市
     * @return
     */
    public List<City> selectCity();
}
