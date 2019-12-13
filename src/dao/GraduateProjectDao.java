package dao;

import domain.*;
import helper.JdbcHelper;
import util.JSONUtil;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class GraduateProjectDao {
	private static GraduateProjectDao graduateProjectDao = new GraduateProjectDao();
	private GraduateProjectDao() {
	}
	public static GraduateProjectDao getInstance() {
		return graduateProjectDao;
	}
    //定义查询整个对象的findAll方法
	public Collection<GraduateProject> findAll() throws SQLException {
		//定义结果集并令graduateProjects指向它
		Set<GraduateProject> graduateProjects = new HashSet<GraduateProject>();
		//建立连接
		Connection connection = JdbcHelper.getConn();
		//创建语句盒子
		Statement statement = connection.createStatement();
		//执行预编译语句
		ResultSet resultSet = statement.executeQuery("select * from graduateproject");
		//定义while循环体，如果下一条记录不为空，创建GraduateProject对象并令graduateProject指向它
		while (resultSet.next()) {
			GraduateProject graduateProject = new GraduateProject(
					resultSet.getInt("id"),
					resultSet.getString("title"),
					GraduateProjectCategoryDao.getInstance().find(resultSet.getInt("graduateProjectCategory_id")),
					GraduateProjectTypeDao.getInstance().find(resultSet.getInt("graduateProjectType_id")),
					GraduateProjectStatusDao.getInstance().find(resultSet.getInt("graduateProjectStatus_id")),
					TeacherDao.getInstance().find(resultSet.getInt("teacher_id")));
			graduateProjects.add(graduateProject);
		}
		//关闭资源
		JdbcHelper.close(resultSet, statement, connection);
		return graduateProjects;
	}

	public boolean add(GraduateProject graduateProject) throws SQLException{
		//建立连接
		Connection connection = JdbcHelper.getConn();
		//创建语句盒子并执行sql语句
		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO graduateproject (title,teacher_id,graduateprojectcategory_id,graduateprojectstatus_id,graduateprojecttype_id) VALUES (?,?,?,?,?)");
		//为相应参数赋值
		preparedStatement.setString(1,graduateProject.getTitle());
		preparedStatement.setInt(2,graduateProject.getTeacher().getId());
		preparedStatement.setInt(3,graduateProject.getGraduateProjectCategory().getId());
		preparedStatement.setInt(4,graduateProject.getGraduateProjectStatus().getId());
		preparedStatement.setInt(5,graduateProject.getGraduateProjectType().getId());
		int affectedRowNum = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRowNum > 0;
	}

	public boolean update(GraduateProject graduateProject) throws SQLException{
		//建立连接
		Connection connection = JdbcHelper.getConn();
		//创建语句盒子并执行sql语句
		PreparedStatement pstmt = connection.prepareStatement("update graduateproject set title = ?,teacher_id = ?,graduateprojectcategory_id = ?,graduateprojectstatus_id = ?,graduateprojecttype_id = ? where id = ?");
		//为相应参数赋值
		pstmt.setString(1,graduateProject.getTitle());
		pstmt.setInt(2,graduateProject.getTeacher().getId());
		pstmt.setInt(3,graduateProject.getGraduateProjectCategory().getId());
		pstmt.setInt(4,graduateProject.getGraduateProjectStatus().getId());
		pstmt.setInt(5,graduateProject.getGraduateProjectType().getId());
		pstmt.setInt(6,graduateProject.getId());
		int affectedRowNum = pstmt.executeUpdate();
		return affectedRowNum > 0;
	}

	public GraduateProject find(Integer id) throws SQLException{
		GraduateProject graduateProject = null;
		//建立连接
		Connection connection = JdbcHelper.getConn();
		//创建语句盒子
		PreparedStatement preparedStatement = connection.prepareStatement("select * from graduateproject where id = ?");
		//为预编译语句参数赋值
		preparedStatement.setInt(1,id);
		//执行预编译语句
		ResultSet resultSet = preparedStatement.executeQuery();
		//如果下一条记录不为空则执行循环
		if(resultSet.next()){
			//创建GraduateProject对象并令graduateProject指向它
			GraduateProjectCategory graduateProjectCategory =
					GraduateProjectCategoryDao.getInstance().find(resultSet.getInt("graduateProjectCategory_id"));
			GraduateProjectType graduateProjectType =
					GraduateProjectTypeDao.getInstance().find(resultSet.getInt("graduateProjectType_id"));
			GraduateProjectStatus graduateProjectStatus =
					GraduateProjectStatusDao.getInstance().find(resultSet.getInt("graduateProjectStatus_id"));
			Teacher teacher = TeacherDao.getInstance().find(resultSet.getInt("teacher_id"));
			graduateProject = new GraduateProject(resultSet.getInt("id"),
					resultSet.getString("title"),
					graduateProjectCategory,
					graduateProjectType,
					graduateProjectStatus,
					teacher);
		}
		//关闭资源
		JdbcHelper.close(resultSet, preparedStatement, connection);
		return graduateProject;
	}

	public boolean delete(Integer id) throws SQLException{
		//建立连接
		Connection connection = JdbcHelper.getConn();
		//创建语句盒子并执行sql语句
		PreparedStatement preparedStatement = connection.prepareStatement("delete from graduateproject where id = ?");
		//为预编译语句参数赋值
		preparedStatement.setInt(1,id);
		int affectedRowNum = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return  affectedRowNum > 0;
	}
}
