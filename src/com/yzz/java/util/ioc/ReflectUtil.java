package com.yzz.java.util.ioc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yzz on 2017/9/17.
 * mail:yzzstyle@163.com
 */

public class ReflectUtil {

    public static <T>  T initObj(Class<T> tClass,Map<String,String[]> map){
        T t = null;
        try {
            //创建对象实例
            t = tClass.newInstance();
            //赋值操作
            for (Map.Entry<String,String[]> en:map.entrySet()) {
                invoke(t,en.getKey(),en.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return t;
        }
    }

    public static <T> void invoke(T t,String name,String[] value) throws Exception{
        String mehodName = getMethodName(name);

        if (value.length>1){
            List<String> list = new ArrayList();
            for (String s:
                 value) {
                list.add(s);
            }
            t.getClass().getMethod(mehodName,list.getClass()).invoke(t,list);
        }else if (value.length==1){
            Object p = value[0];
            t.getClass().getMethod(mehodName,p.getClass()).invoke(t,p);
        }else {
            t.getClass().getMethod(mehodName,String.class).invoke(t,"undefined");
        }


    }

    public static String getMethodName(String name){
        String uper = name.substring(0,1).toUpperCase();
        String set = "set";
        String end = name.substring(1,name.length());
        return set+uper+end;
    }
}
