package com.xupt.o2o.dao;

import com.xupt.o2o.BaseTest;
import com.xupt.o2o.entity.Area;
import com.xupt.o2o.entity.PersonInfo;
import com.xupt.o2o.entity.Shop;
import com.xupt.o2o.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @ClassName ShopDaoTest
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/5 16:36
 * @Version 1.0
 **/
public class ShopDaoTest extends BaseTest {
    @Autowired
    ShopDao shopDao;
    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner=new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setOwner(owner);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum = shopDao.insertShop(shop);
        Assert.assertEquals(1,effectedNum);
    }
    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        Assert.assertEquals(1,effectedNum);
    }
}
