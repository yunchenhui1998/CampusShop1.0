package com.xupt.o2o.service.impl;

import com.xupt.o2o.dao.ShopCategoryDao;
import com.xupt.o2o.entity.ShopCategory;
import com.xupt.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ShopCategoryServiceImpl
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/10 17:23
 * @Version 1.0
 **/
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
