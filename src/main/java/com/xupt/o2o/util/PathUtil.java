package com.xupt.o2o.util;

/**
 * @ClassName PathUtil
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/6 20:22
 * @Version 1.0
 **/
public class PathUtil {
    private static String seperator="/";
    public static String getImgBasePath(){
        String basePath="";
        basePath="E:/projectdev/image/";
        basePath=basePath.replace("/",seperator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath="upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",seperator);
    }
}
