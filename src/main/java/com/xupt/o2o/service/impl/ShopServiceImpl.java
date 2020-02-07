package com.xupt.o2o.service.impl;

import com.sun.imageio.plugins.common.ImageUtil;
import com.xupt.o2o.Exception.ShopOperationException;
import com.xupt.o2o.dao.ShopDao;
import com.xupt.o2o.dto.ShopExecution;
import com.xupt.o2o.entity.Shop;
import com.xupt.o2o.enums.ShopStateEnum;
import com.xupt.o2o.service.ShopService;
import com.xupt.o2o.util.ImgUtil;
import com.xupt.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * @ClassName ShopServiceImpl
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/7 15:50
 * @Version 1.0
 **/
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        //空值判断
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        /*try{*/
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if(effectedNum<=0){
                throw new ShopOperationException("店铺创建失败");
            }else{
                if(shopImg!=null){
//                    try {
                        addShopImg(shop,shopImg);
//                    }catch (Exception e){
//                        throw new ShopOperationException("addShopImg error:"+e.getMessage());
//                    }
                    //更新店铺的图片地址
                    effectedNum=shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        /*}catch (Exception e){
            throw new ShopOperationException("addShop error:"+e.getMessage());

        }*/
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, File shopImg) {
        //获取shop图片目录的相对值路径
        String dest= PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr= ImgUtil.generateThumbnail(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }
}
