package com.yzz.java.base;
import com.yzz.java.util.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yzz on 2017/9/6.
 * mail:yzzstyle@163.com
 */

public class BaseAction extends HttpServlet{
    protected HttpServletRequest req;
    protected HttpServletResponse resp;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        String method = (String) req.getAttribute("method");
        try {
            Log.e(""+getClass().getMethod(method));
            this.getClass().getMethod(method).invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
