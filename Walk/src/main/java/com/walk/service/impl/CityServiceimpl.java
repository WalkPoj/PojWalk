package com.walk.service.impl;

import com.walk.dao.CityDao;
import com.walk.pojo.City;
import com.walk.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceimpl implements CityService {

    @Autowired
    private CityDao cdao;

    @Override
    public List<City> selectCity() {
        return this.cdao.selectCity();
    }
}
