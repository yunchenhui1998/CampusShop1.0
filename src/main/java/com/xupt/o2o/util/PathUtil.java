package com.xupt.o2o.util;

/**
 * @ClassName PathUtil
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/6 20:22
 * @Version 1.0
 **/
public class PathUtil {
    private static String separator=System.getProperty("file.separator");
    public static String getImgBasePath(){
        String os=System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")){
            basePath="E:/projectdev/image/";
        }else{
            basePath="/home/ych/image/";
        }
        basePath=basePath.replace("/",separator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath="/upload/item/shop/"+shopId+"/";
        return imagePath.replace("/",separator);
    }
}
