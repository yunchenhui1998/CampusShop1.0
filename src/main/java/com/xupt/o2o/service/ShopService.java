package com.xupt.o2o.service;

import com.xupt.o2o.dto.ShopExecution;
import com.xupt.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName);
}
