package com.yzz.java.action.cookie;

import com.yzz.java.bean.Account;
import com.yzz.java.bean.User;
import com.yzz.java.util.ioc.BaseAction;
import com.yzz.java.util.Log;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by yzz on 2017/9/6.
 * mail:yzzstyle@163.com
 */

public class CookieServlet extends BaseAction {

    public void setCookie() throws Exception{
        String name = req.getParameter("name");
        Log.e(name);
        Map<String,String[]> map = req.getParameterMap();
        for (Map.Entry<String,String[]> en:map.entrySet()){
            for (String str:en.getValue()) {
                String encodestr = URLEncoder.encode(str,"utf-8");
                Log.e(encodestr);
                Cookie cookie = new Cookie(en.getKey(),encodestr);
                cookie.setPath(getServletContext().getContextPath());
                cookie.setHttpOnly(true);
                cookie.setMaxAge(10);
                resp.addCookie(cookie);
            }
        }
        resp.getWriter().write("已保存cookie");
    }

    public void deleteCookie() throws Exception{
        Cookie[] cookie = req.getCookies();
        for (Cookie c:cookie){
            c.setMaxAge(0);
            resp.addCookie(c);
        }
        resp.getWriter().write("删除成功");
    }

    public void test(User account){
        try {
            resp.getWriter().write(account.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
