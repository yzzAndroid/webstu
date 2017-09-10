package com.yzz.java.util;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * Created by yzz on 2017/9/8.
 * mail:yzzstyle@163.com
 */

public class Utils {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public  static boolean isAjaxRequest(HttpServletRequest request){
        String flag = request.getHeader("x-requested-with");
        if (flag!=null&&"XMLHttpRequest".equalsIgnoreCase(flag)){
            return true;
        }
        return false;
    }

    public static String getTime(){
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
