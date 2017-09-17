package com.yzz.java.filter;

import com.yzz.java.util.Log;
import com.yzz.java.util.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;

/**
 * Created by yzz on 2017/9/5.
 * mail:yzzstyle@163.com
 */

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       filterConfig.getServletContext().setAttribute("userCount",0);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        Log.e(Utils.getTime()+"Encoding-do");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //不允许浏览器端或缓存服务器缓存当前页面信息。
        response.setHeader( "Pragma", "no-cache" );
        response.setDateHeader("Expires", 0);
        response.addHeader( "Cache-Control", "no-cache" );//浏览器和缓存服务器都不应该缓存页面信息
        response.addHeader( "Cache-Control", "no-store" );//请求和响应的信息都不应该被存储在对方的磁盘系统中；
        response.addHeader( "Cache-Control", "must-revalidate" );///于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        String contextPath = request.getServletContext().getContextPath();
        if ((contextPath+"/").equals(uri)){
            if (session.getAttribute("login-success")!=null){
                //销毁session
                request.getSession().invalidate();
                synchronized (request.getServletContext()) {
                    int count = (int) request.getServletContext().getAttribute("userCount");
                    request.getServletContext().setAttribute("userCount",--count);
                    Log.e(""+request.getServletContext().getAttribute("userCount"));
                }
                Log.e("主页销毁session"+System.currentTimeMillis());
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        Log.e("Encoding-destroy");
    }
}
