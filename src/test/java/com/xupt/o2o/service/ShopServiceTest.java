package com.xupt.o2o.service;

import com.xupt.o2o.BaseTest;
import com.xupt.o2o.dto.ShopExecution;
import com.xupt.o2o.entity.Area;
import com.xupt.o2o.entity.PersonInfo;
import com.xupt.o2o.entity.Shop;
import com.xupt.o2o.entity.ShopCategory;
import com.xupt.o2o.enums.ShopStateEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

/**
 * @ClassName ShopServiceTest
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/7 16:20
 * @Version 1.0
 **/
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void testAddShop(){
        Shop shop = new Shop();
        PersonInfo owner=new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setShopId(1L);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setOwner(owner);
        shop.setShopName("测试的店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg=new File("E:\\projectdev\\image\\xiaohuangren.jpg");
        ShopExecution se=shopService.addShop(shop,shopImg);
        Assert.assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }
}
