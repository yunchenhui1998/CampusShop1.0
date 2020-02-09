package com.xupt.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName ShopAdminController
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/9 21:02
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }
}
