package com.xupt.o2o.dao;

import com.xupt.o2o.BaseTest;
import com.xupt.o2o.entity.Shop;
import com.xupt.o2o.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName ShopCategoryDaoTest
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/10 16:56
 * @Version 1.0
 **/
public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(new ShopCategory());
        Assert.assertEquals(2,shopCategoryList.size());
        ShopCategory testCategory=new ShopCategory();
        ShopCategory parentCategory=new ShopCategory();
        parentCategory.setShopCategoryId(1L);
        testCategory.setParent(parentCategory);
        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        Assert.assertEquals(1,shopCategoryList.size());
        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }
}
