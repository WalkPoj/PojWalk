package com.walk.service;

import com.walk.pojo.City;

import java.util.List;

public interface CityService {
    /**
     * 查询所有城市
     * @return
     */
    public List<City> selectCity();
}
