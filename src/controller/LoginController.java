package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import domain.User;
import service.UserService;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login.ctl")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //定义string类型的username、password字段分别等于请求得到的参数username、password
        String user_json = JSONUtil.getJSON(request);
        User user = JSON.parseObject(user_json,User.class);
        String username = user.getUsername();
        String password = user.getPassword();
        //创建json对象message，以便于向前端响应信息
        JSONObject message = new JSONObject();
        try {
            //调用userService的login方法并传入相应参数并令User类型的loggedUser指向它
            User loggedUser = UserService.getInstance().login(username,password);
            //如果不为空
            if (loggedUser != null){
                //如果当前请求对应服务器内存中的一个session对象，返回该对象
                //如果当前服务器内存中的没有session对象与当前请求对应，则建立一个session对象并返回该对象
                HttpSession session =request.getSession();
                //10分钟未操作则session失效
                session.setMaxInactiveInterval(10 * 60);
                session.setAttribute("currentUser",loggedUser);
                String teacher_json = JSONObject.toJSONString(loggedUser.getTeacher());
                //响应并打印出数据信息
               response.getWriter().println(teacher_json);
                //此处应重定向到索引页（菜单页）
                return;
            }else {
                message.put("message","用户名或密码错误");
            }
        } catch (SQLException e) {
            //得到具体错误信息
            e.printStackTrace();
            message.put("message","数据库操作异常");
        }catch (Exception e) {
            e.printStackTrace();
            message.put("message","网络异常");
        }
        //响应message到前端
        response.getWriter().println(message);
    }
}
