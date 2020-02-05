package com.xupt.o2o.dao;

import com.xupt.o2o.entity.Shop;

public interface ShopDao {
    /*
     * @Author yunchenhui
     * @Description //TODO 新增店铺
     * @Date 15:58 2020/2/5
     * @Param shop
     * @return
     **/
    int insertShop(Shop shop);
    /*
     * @Author yunchenhui
     * @Description //TODO 更新店铺
     * @Date 16:53 2020/2/5
     * @Param 
     * @return 
     **/
    int updateShop(Shop shop);
}
