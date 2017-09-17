package com.yzz.java.util.ioc;
import com.yzz.java.util.Log;
import com.yzz.java.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzz on 2017/9/6.
 * mail:yzzstyle@163.com
 */

public class BaseAction extends HttpServlet{
    protected HttpServletRequest req;
    protected HttpServletResponse resp;
    private static Map<String,Method> methodMap;

    static {

    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (methodMap==null)initMap();
        Log.e(Utils.getTime()+"BaseAction:service");
        this.req = req;
        this.resp = resp;
        String method = (String) req.getAttribute("method");
        try {
            Log.e(""+method);
            if (methodMap.get(method) == null)
                this.getClass().getMethod(method).invoke(this);
            else {
                Class tclass = methodMap.get(method).getParameterTypes()[0];
                Object arg = ReflectUtil.initObj(tclass,req.getParameterMap());
                methodMap.get(method).invoke(this, arg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMap(){
        methodMap = new HashMap<>();
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method:
                methods) {
            method.setAccessible(true);
            Class[] c = method.getParameterTypes();
            if (c.length==1){
                methodMap.put(method.getName(),method);
            }
            if (c.length==0){
                methodMap.put(method.getName(),null);
            }
        }
        Log.e(methodMap.toString());
    }
}
