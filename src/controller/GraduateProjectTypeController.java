package controller;

import domain.GraduateProjectType;
import service.DepartmentService;
import service.GraduateProjectTypeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.JSONUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/graduateProjectType.ctl")
public class GraduateProjectTypeController extends HttpServlet {
    /**
     * 方法-功能
     * put 修改
     * post 添加
     * delete 删除
     * get 查找
     */
    //POST 49.235.219.168:8080/bysj/graduateProjectType.ctl
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //根据request对象，获得代表参数的JSON字串
        String graduateProjectType_json = JSONUtil.getJSON(request);
        //将JSON字串解析为GraduateProjectType对象
        GraduateProjectType graduateProjectTypeToAdd = JSON.parseObject(graduateProjectType_json, GraduateProjectType.class);
        System.out.println(graduateProjectTypeToAdd);
        //创建JSON对象
        JSONObject resp = new JSONObject();
        try {
            //增加加GraduateProjectType对象
            GraduateProjectTypeService.getInstance().add(graduateProjectTypeToAdd);
            //加入数据信息
            resp.put("message", "添加成功");
        }catch (SQLException e){
            //打印具体错误信息
            e.printStackTrace();
            resp.put("message", "数据库操作异常");
        }catch (Exception e) {
            //打印具体错误信息
            e.printStackTrace();
            resp.put("message", "网络异常");
        }
        //响应
        response.getWriter().println(resp);
    }
    //DELETE 49.235.219.168:8080/bysj/graduateProjectType.ctl
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        //创建JSON对象
        JSONObject resp = new JSONObject();
        try{
            //到数据库表中删除
            DepartmentService.getInstance().delete(id);
            //加入数据信息
            resp.put("message", "删除成功");
        }catch (SQLException e){
            e.printStackTrace();
            resp.put("message", "数据库操作异常");
        }catch (Exception e){
            e.printStackTrace();
            resp.put("message", "网络异常");
        }
        //向客户端响应数据
        response.getWriter().println(resp);
    }
    //PUT 49.235.219.168:8080/bysj/graduateProjectType.ctl
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //将JSON字串解析为GraduateProjectType对象
        String graduateProjectType_json = JSONUtil.getJSON(request);
        GraduateProjectType graduateProjectTypeToAdd = JSON.parseObject(graduateProjectType_json, GraduateProjectType.class);
        //创建JSON对象
        JSONObject resp = new JSONObject();
        try{
            //增加加GraduateProjectType对象
            GraduateProjectTypeService.getInstance().update(graduateProjectTypeToAdd);
            //加入数据信息
            resp.put("message", "修改成功");
        }catch (SQLException e){
            e.printStackTrace();
            resp.put("message", "数据库操作异常");
        }catch (Exception e){
            e.printStackTrace();
            resp.put("message", "网络异常");
        }
        //响应
        response.getWriter().println(resp);
    }
    //GET 49.235.219.168:8080/bysj/graduateProjectType.ctl
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
                responseGraduateProjectTypes(response);
            } else {
                int id = Integer.parseInt(id_str);
                responseDegree(id, response);
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
    private void responseDegree(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        //根据id查找
        GraduateProjectType graduateProjectType = GraduateProjectTypeService.getInstance().find(id);
        String graduateProjectType_json = JSON.toJSONString(graduateProjectType);
        //控制台打印结果
        System.out.println(graduateProjectType_json);
        //浏览器展示结果
        response.getWriter().println(graduateProjectType_json);
    }
    //响应所有对象
    private void responseGraduateProjectTypes(HttpServletResponse response)
            throws ServletException, IOException,SQLException {
        //获得所有
        Collection<GraduateProjectType> graduateProjectTypes = GraduateProjectTypeService.getInstance().findAll();
        String graduateProjectTypes_json = JSON.toJSONString(graduateProjectTypes);
        //控制台打印结果
        System.out.println(graduateProjectTypes_json);
        //浏览器展示结果
        response.getWriter().println(graduateProjectTypes_json);
    }
}
