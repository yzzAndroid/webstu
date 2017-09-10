package com.yzz.java.filter;

import com.sun.deploy.net.HttpRequest;
import com.yzz.java.util.Log;
import com.yzz.java.util.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yzz on 2017/9/7.
 * mail:yzzstyle@163.com
 */

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Log.e(System.currentTimeMillis()+"yzzweb"+"login-filter");
        Log.e(request.getRequestURL().toString());

        String loginUrl = request.getServletContext().getContextPath()+"/page/login.html";
        if (request.getRequestURL().toString().contains("login.html")){
            if (session.getAttribute("login-success")!=null) {
                session.invalidate();
                synchronized (request.getServletContext()) {
                    int count = (int) request.getServletContext().getAttribute("userCount");
                    request.getServletContext().setAttribute("userCount",--count);
                    Log.e(""+request.getServletContext().getAttribute("userCount"));
                }
                Log.e(Utils.getTime() + "yzzweb" + "销毁session");
            }
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //判断是否是ajax请求
        if (session.getAttribute("login-success")!=null)
            filterChain.doFilter(servletRequest,servletResponse);
        else {
            if (Utils.isAjaxRequest(request)) {
                //Ajax
                response.setHeader("login-url", loginUrl);
                response.getWriter().write("请重新登录");
            } else {
                response.sendRedirect(loginUrl);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
