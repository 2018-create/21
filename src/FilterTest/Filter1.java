package FilterTest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter1",urlPatterns = {"/*"})
public  class Filter1 implements javax.servlet.Filter {
    public void destroy(){
    }
    public void  doFilter(ServletRequest req , ServletResponse resp, FilterChain chain)throws ServletException, IOException{
        System.out.println("Filter 1 begins");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        //获得请求路径
        String path= request.getRequestURI();
        //判断请求的路径是否包含login，若包含则提示不设置字符编码
        if (path.contains("/login")){
            System.out.println("未设置字符编码格式");
        }else {//若路径符合条件，则首先设置响应对象字符编码格式为utf8
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("设置响应对象字符编码格式为utf8");
            //对调用的方法进行判断，若为post或put则设置请求对象字符编码格式为utf8
            if (request.getMethod().equals("POST")||request.getMethod().equals("PUT")){
                request.setCharacterEncoding("UTF-8");
                System.out.println("设置请求对象字符编码格式为utf8");
            }
        }
        chain.doFilter(req,resp);//执行其他过滤器，如过滤器已执行完毕，则执行原请求
        System.out.println("Filter 1 ends");
    }
    public void init(FilterConfig config)throws ServletException{
    }
}
