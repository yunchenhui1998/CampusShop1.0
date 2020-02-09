package com.xupt.o2o.util;

/**
 * @ClassName PathUtil
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/9 16:31
 * @Version 1.0
 **/
public class PathUtil {
    public static String getImgBasePath(){
        return "E:\\projectdev\\image\\";
    }
    public static String getShopImagePath(long shopId){
        String imagePath="upload\\item\\shop\\"+shopId+"\\";
        return imagePath;
    }
}
