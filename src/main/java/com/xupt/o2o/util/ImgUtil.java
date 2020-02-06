package com.xupt.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName ImgUtil
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/6 20:16
 * @Version 1.0
 **/
public class ImgUtil {
    private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r=new Random();
    private static Logger logger= LoggerFactory.getLogger(ImgUtil.class);
    /*
     * @Author yunchenhui
     * @Description //TODO 将CommonsMultipartFile转换为File
     * @Date 21:14 2020/2/6
     * @Param [cFile]
     * @return java.io.File
     **/
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile=new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }
    /*
     * @Author yunchenhui
     * @Description //TODO 处理缩略图，并返回新生成图片的相对值路径
     * @Date 21:03 2020/2/6
     * @Param [thumbnail, targetAddr]
     * @return java.lang.String
     **/
    public static String generateThumbnail(File thumbnail,String targetAddr){
        String realFileName=getRandomFileName();
        String extension=getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        logger.debug("current relativeAddr is:"+relativeAddr);
        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("current complete addr is:"+ PathUtil.getImgBasePath()+relativeAddr);
        try{
            Thumbnails.of(thumbnail).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }
    /*
     * @Author yunchenhui
     * @Description //TODO 生成随机文件名，当前年月日小时分钟秒+5位随机数
     * @Date 20:48 2020/2/6
     * @Param 
     * @return 
     **/
    private static String getRandomFileName(){
        int randnum=r.nextInt(89999)+10000;
        String nowTimeStr=sDateFormat.format(new Date());
        return nowTimeStr+randnum;
    }
    private static String getFileExtension(File cFile){
        String originFileName=cFile.getName();
        return originFileName.substring(originFileName.lastIndexOf("."));
    }
    private static void makeDirPath(String targetAddr){
        String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
        File dirPath=new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdir();
        }
    }
}
