package com.xupt.o2o.service;

import com.xupt.o2o.BaseTest;
import com.xupt.o2o.dto.ShopExecution;
import com.xupt.o2o.entity.Area;
import com.xupt.o2o.entity.PersonInfo;
import com.xupt.o2o.entity.Shop;
import com.xupt.o2o.entity.ShopCategory;
import com.xupt.o2o.enums.ShopStateEnum;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

/**
 * @ClassName ShopServiceTest
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/9 17:23
 * @Version 1.0
 **/
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void testModifyShop() throws RuntimeException,FileNotFoundException{
        Shop shop=new Shop();
        shop.setShopId(47L);
        shop.setShopName("修改后的店铺名称");
        File shopImg=new File("E:\\projectdev\\image\\yunchenhui.jpg");
        InputStream is=new FileInputStream(shopImg);
        ShopExecution shopExecution=shopService.modifyShop(shop,is,"yunchenhui.jpg");
        System.out.println("新的图片地址:"+shopExecution.getShop().getShopImg());
    }
    @Test
    @Ignore
    public void testAddShop() throws FileNotFoundException {
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
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg=new File("E:\\projectdev\\image\\xiaohuangren.jpg");
        InputStream is=new FileInputStream(shopImg);
        ShopExecution se=shopService.addShop(shop,is,shopImg.getName());
        Assert.assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }
}
