package com.yzz.java.action.login;

import com.yzz.java.util.Log;
import com.yzz.java.util.MD5Utils;
import com.yzz.java.util.Utils;
import net.sf.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yzz on 2017/9/5.
 * mail:yzzstyle@163.com
 */

public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       String password = req.getParameter("password");
        HttpSession session = req.getSession();

        String success = getServletContext().getContextPath()+"/index.jsp";
        String faile = getServletContext().getContextPath()+"/action/register/register.do";
        JSONObject jsonObject = new JSONObject();
        if ("yzz".equals(name)&&"bemy1004".equals(password)) {
            jsonObject.put("state", "1");
            jsonObject.put("url", success);
            session.setAttribute("login-success","success");
            Log.e(Utils.getTime()+"登录成功");
            synchronized (getServletContext()) {
                int count = (int)getServletContext().getAttribute("userCount");
                getServletContext().setAttribute("userCount",++count);
                Log.e(""+getServletContext().getAttribute("userCount"));
            }
        }else{
            jsonObject.put("state", "0");
            jsonObject.put("url", faile);
        }
        resp.getWriter().write(jsonObject.toString());
    }
}
