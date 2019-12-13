package controller;

import domain.GraduateProject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import service.GraduateProjectService;
import util.JSONUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/graduateProject.ctl")
public class GraduateProjectController extends HttpServlet {
    /**
     * 方法-功能
     * put 修改
     * post 添加
     * delete 删除
     * get 查找
     */
    //POST 49.235.219.168:8080/bysj/graduateProject.ctl
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //根据request对象，获得代表参数的JSON字串
        String graduateProject_json = JSONUtil.getJSON(request);
        //将JSON字串解析为GraduateProject对象
        GraduateProject graduateProjectToAdd = JSON.parseObject(graduateProject_json, GraduateProject.class);
        System.out.println(graduateProjectToAdd);
        //创建JSON对象
        JSONObject resp = new JSONObject();
        try {
            //增加加GraduateProject对象
            GraduateProjectService.getInstance().add(graduateProjectToAdd);
            //加入数据信息
            resp.put("MSG", "添加成功");
        }catch (SQLException e){
            e.printStackTrace();
            resp.put("MSG", "数据库操作异常");
        }catch (Exception e){
            e.printStackTrace();
            resp.put("MSG", "网络异常");
        }
        //响应
        response.getWriter().println(resp);
    }
    //DELETE 49.235.219.168:8080/bysj/graduateProjectType.ctl
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        //将id_str转化为int类型
        int id = Integer.parseInt(id_str);
        //创建JSON对象
        JSONObject resp = new JSONObject();
        try{
            //到数据库表中删除
            GraduateProjectService.getInstance().delete(id);
            //添加数据信息
            resp.put("MSG", "删除成功");
        }catch (SQLException e){
            e.printStackTrace();
            resp.put("MSG", "数据库操作异常");
        }catch (Exception e){
            e.printStackTrace();
            resp.put("MSG", "网络异常");
        }
        //响应
        response.getWriter().println(resp);
    }
    //PUT 49.235.219.168:8080/bysj/graduateProjectType.ctl
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String graduateProject_json = JSONUtil.getJSON(request);
        //将JSON字串解析为GraduateProject对象
        GraduateProject graduateProjectToAdd = JSON.parseObject(graduateProject_json, GraduateProject.class);
        //创建JSON对象
        JSONObject resp = new JSONObject();
        try{
            //增加加GraduateProjectType对象
            GraduateProjectService.getInstance().update(graduateProjectToAdd);
            //加入数据信息
            resp.put("MSG", "修改成功");
        }catch (SQLException e){
            e.printStackTrace();
            resp.put("MSG", "数据库操作异常");
        }catch (Exception e){
            e.printStackTrace();
            resp.put("MSG", "网络异常");
        }
        //响应
        response.getWriter().println(resp);
    }
    //GET 49.235.219.168:8080/bysj/graduateProject.ctl
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            //如果id = null, 表示响应所有对象，否则响应id指定的对象
            if (id_str == null) {
                responseGraduateProjects(response);
            } else {
                int id = Integer.parseInt(id_str);
                responseGraduateProject(id, response);
            }
        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            //响应message到前端
            response.getWriter().println(message);
        }catch(Exception e){
            message.put("message", "网络异常");
            //响应message到前端
            response.getWriter().println(message);
        }
    }
    //响应一个对象
    private void responseGraduateProject(int id, HttpServletResponse response)
            throws ServletException, IOException,SQLException {
        //根据id查找
        GraduateProject graduateProject = GraduateProjectService.getInstance().find(id);
        String graduateProject_json = JSON.toJSONString(graduateProject);
        //控制台打印结果
        System.out.println(graduateProject_json);
        //浏览器展示结果
        response.getWriter().println(graduateProject_json);
    }
    //响应所有对象
    private void responseGraduateProjects(HttpServletResponse response)
            throws ServletException, IOException,SQLException {
        //获得所有
        Collection<GraduateProject> graduateProjects = GraduateProjectService.getInstance().findAll();
        String graduateProjects_json = JSON.toJSONString(graduateProjects);
        //控制台打印结果
        System.out.println(graduateProjects_json);
        //浏览器展示结果
        response.getWriter().println(graduateProjects_json);
    }
}