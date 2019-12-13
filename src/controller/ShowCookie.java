package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showCookies.Session")
public class ShowCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到所有的cookie信息
        Cookie[] allCookies =  request.getCookies();
        //如果数组为空
        if (allCookies == null){
            //响应并打印数据信息
            response.getWriter().println("no cookies available");
        }else {
            //在allCookies数组中遍历cookie对象
            for (Cookie cookie:allCookies){
                //响应并打印数据信息
                response.getWriter().println(cookie.getName() + "=" + cookie.getValue());
            }
        }
    }
}
