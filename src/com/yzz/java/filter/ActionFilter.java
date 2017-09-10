package com.yzz.java.filter;


import com.yzz.java.util.Log;
import com.yzz.java.util.Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yzz on 2017/9/10.
 * mail:yzzstyle@163.com
 */

public class ActionFilter implements Filter {
    private String sqlit;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String s = filterConfig.getInitParameter("sqlit");
        if (s==null||s.equals("")){
            sqlit = "!";
        }else sqlit = s;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Log.e(Utils.getTime()+"ActionFilter:yzzweb");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String[] strs = req.getRequestURI().toString().split(sqlit);
        if (strs.length>1)
            servletRequest.setAttribute("method",strs[1]);

        String context = servletRequest.getServletContext().getContextPath();
        String dispatcherPath = strs[0].replace(context,"");
        servletRequest.getRequestDispatcher(dispatcherPath).forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
