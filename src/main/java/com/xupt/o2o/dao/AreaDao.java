package com.xupt.o2o.dao;

import com.xupt.o2o.entity.Area;

import java.util.List;

public interface AreaDao {
    /**
     * 列出区域列表
     * @return arealist
     */
    List<Area> queryArea();
}
