package com.xupt.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName ImageUtil
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/9 16:26
 * @Version 1.0
 **/
public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r=new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }
    public static String generateThumbnail(InputStream thumbnailInputStream, String fileName,String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:"+relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete addr is:"+PathUtil.getImgBasePath()+relativeAddr);
        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200)
                    .watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File(basePath + "\\watermark.jpg")), 0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }
    public static String getRandomFileName(){
        int rannum=r.nextInt(89999)+10000;
        String nowTimeStr=sDateFormat.format(new Date());
        return nowTimeStr+rannum;
    }
    private static String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    private static void makeDirPath(String targetAddr){
        String realFileParentFile = PathUtil.getImgBasePath()+targetAddr;
        File dirPath=new File(realFileParentFile);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }
    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("E:\\projectdev\\image\\xiaohuangren.jpg"))
                .size(200, 200)
                .watermark(Positions.BOTTOM_LEFT, ImageIO.read(new File(basePath + "\\watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("E:\\projectdev\\image\\xiaohuangrennew.jpg");
    }
}
