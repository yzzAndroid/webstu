package com.yzz.java.action.register;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yzz on 2017/9/6.
 * mail:yzzstyle@163.com
 */

public class RegistServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html =
                "<html>" +
                "<head>" +
                        "<title>注册</title>" +
                        "</head>" +
                "<body>" +
                        "欢迎进入yzzweb的注册页面" +
                        "</body>" +
                "</html>";
        resp.getWriter().write(html);
    }
}
