package com.xupt.o2o.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CodeUtil
 * @Description TODO
 * @Author yunchenhui
 * @Date 2020/2/12 20:01
 * @Version 1.0
 **/
public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY
        );
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if(verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }
}
