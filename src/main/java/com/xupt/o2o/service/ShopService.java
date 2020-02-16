package com.xupt.o2o.service;

import com.xupt.o2o.dto.ShopExecution;
import com.xupt.o2o.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /*
     * @Author yunchenhui
     * @Description //TODO 通过店铺id获得店铺信息
     * @Date 16:55 2020/2/15
     * @Param [shopId]
     * @return com.xupt.o2o.entity.Shop
     **/
    Shop getByShopId(long shopId);
    /*
     * @Author yunchenhui
     * @Description //TODO 更新店铺信息，包括图片的处理
     * @Date 16:58 2020/2/15
     * @Param [shop, shopImgInputStream, fileName]
     * @return com.xupt.o2o.dto.ShopExecution
     **/
    ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName) throws RuntimeException;
    /*
     * @Author yunchenhui
     * @Description //TODO 注册店铺信息，包括图片的处理
     * @Date 16:54 2020/2/15
     * @Param [shop, shopImgInputStream, fileName]
     * @return com.xupt.o2o.dto.ShopExecution
     **/
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName);
}
