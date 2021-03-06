package com.xupt.o2o.service.impl;

import com.xupt.o2o.dao.ShopDao;
import com.xupt.o2o.dto.ShopExecution;
import com.xupt.o2o.entity.Shop;
import com.xupt.o2o.enums.ShopStateEnum;
import com.xupt.o2o.service.ShopService;
import com.xupt.o2o.util.ImageUtil;
import com.xupt.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * @ClassName ShopServiceImpl
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/9 17:05
 * @Version 1.0
 **/
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws RuntimeException {
        if(shop==null||shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else{        //1.判断是否需要处理图片
            try {
                if (shopImgInputStream != null && fileName != null && !"".equals(fileName)) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectNum = shopDao.updateShop(shop);
                if (effectNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            }catch (Exception e){
                throw new RuntimeException("modifyShop error:"+e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum=shopDao.insertShop(shop);
            System.out.println(shop.getShopId());
            if(effectedNum<=0){
                throw new RuntimeException("店铺创建失败");
            }else{
                if(shopImgInputStream !=null){
                    try {
                        addShopImg(shop, shopImgInputStream,fileName);
                    }catch (Exception e){
                        throw new RuntimeException("addShopImg error:"+e.getMessage());
                    }
                    effectedNum=shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new RuntimeException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException("addShop error:"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }
    private void addShopImg(Shop shop,InputStream shopImgInputStream,String fileName){
        String dest= PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr= ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}
