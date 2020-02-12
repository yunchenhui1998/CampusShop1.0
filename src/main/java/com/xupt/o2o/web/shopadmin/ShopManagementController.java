package com.xupt.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xupt.o2o.dto.ShopExecution;
import com.xupt.o2o.entity.Area;
import com.xupt.o2o.entity.PersonInfo;
import com.xupt.o2o.entity.Shop;
import com.xupt.o2o.entity.ShopCategory;
import com.xupt.o2o.enums.ShopStateEnum;
import com.xupt.o2o.service.AreaService;
import com.xupt.o2o.service.ShopCategoryService;
import com.xupt.o2o.service.ShopService;
import com.xupt.o2o.util.CodeUtil;
import com.xupt.o2o.util.HttpServletRequestUtil;
import com.xupt.o2o.util.ImageUtil;
import com.xupt.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShopManagementController
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/9 19:29
 * @Version 1.0
 **/
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        List<ShopCategory> shopCategoryList=new ArrayList<ShopCategory>();
        List<Area>  areaList=new ArrayList<Area>();
        try {
            shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList=areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(!CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }
        //1.接受并转换相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }
        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            //Session TODO
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
                if(se.getState()== ShopStateEnum.CHECK.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }

            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
        //3.返回结果
    }

//    private static void InputStreamToFile(InputStream is, File file) {
//        FileOutputStream os=null;
//        try{
//            os=new FileOutputStream(file);
//            int bytesRead=0;
//            byte[] buffer=new byte[1024];
//            while((bytesRead=is.read(buffer))!=-1){
//                os.write(buffer,0,bytesRead);
//            }
//        }catch (IOException e){
//            throw new RuntimeException("调用inputStreamToFile产生异常:"+e.getMessage());
//        }finally {
//            try{
//                if(os!=null){
//                    os.close();
//                }
//                if(is!=null){
//                    is.close();
//                }
//            }catch (IOException e){
//                throw new RuntimeException("inputStreamToFile关闭IO异常:"+e.getMessage());
//            }
//        }
//    }
}
