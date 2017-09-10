package com.yzz.java.action.download;

import com.yzz.java.util.EncodeUtils;
import com.yzz.java.util.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Created by yzz on 2017/9/4.
 * mail:yzzstyle@163.com
 */

public class DownloadAction extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fileName =  req.getParameter("filename");
            //得到文件的绝对路径
            String path = getServletContext().getRealPath("res/download/" + fileName);
            //告知浏览器文件的类型
            resp.setContentType(getServletContext().getMimeType(path));
            //告示浏览器文件的打开方式是下载
            String userAgent = req.getHeader("User-Agent");
            resp.setHeader("Content-Disposition", "attachment;filename=" + EncodeUtils.chenageBrowserEncode(userAgent,fileName));
            InputStream in = new FileInputStream(path);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) > 0) {
                resp.getOutputStream().write(buf, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
