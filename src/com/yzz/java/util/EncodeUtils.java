package com.yzz.java.util;

import java.net.URLEncoder;

/**
 * Created by yzz on 2017/9/5.
 * mail:yzzstyle@163.com
 */

public class EncodeUtils {

    public static String chenageBrowserEncode(String userAgent,String parmenter) throws Exception{
        if (null==userAgent)return "";
        if (userAgent.contains("Firefox")){
            //火狐
           parmenter = new String(parmenter.getBytes("utf-8"),"iso-8859-1");

        }else if (userAgent.contains("MSIE")){
            //IE
            parmenter = URLEncoder.encode(parmenter,"utf-8");
            parmenter = parmenter.replace("+","");
        }else {
            //其他
            parmenter = new String(parmenter.getBytes("utf-8"),"iso-8859-1");

        }

        return parmenter;
    }
}
