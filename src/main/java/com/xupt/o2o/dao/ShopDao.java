package com.xupt.o2o.dao;

import com.xupt.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    /*
     * @Author yunchenhui
     * @Description //TODO 根据id查询店铺信息
     * @Date 16:10 2020/2/15
     * @Param
     * @return
     **/
    Shop queryByShopId(long shopId);
    /*
     * @Author yunchenhui
     * @Description //TODO 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域ID，OWNER
     * @Date 16:48 2020/2/16
     * @Param shopCondition 查询的条件
     * @Param rowIndex 从第几行开始取
     * @Param pageSize 返回的条数
     * @return
     **/
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
                             @Param("rowIndex")int rowIndex,
                             @Param("pageSize") int pageSize);
    /*
     * @Author yunchenhui
     * @Description //TODO 返回queryShopList总数
     * @Date 17:09 2020/2/16
     * @Param 
     * @return 
     **/
    int queryShopCount(@Param("shopCondition")Shop shopCondition);
}
