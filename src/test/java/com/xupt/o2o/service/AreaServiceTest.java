package com.xupt.o2o.service;

import static org.junit.Assert.*;
import com.xupt.o2o.BaseTest;
import com.xupt.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void testGetAreaList(){
        List<Area> areaList=areaService.getAreaList();
        assertEquals("西区",areaList.get(0).getAreaName());
    }
}
