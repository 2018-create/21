package dao;

import domain.GraduateProjectCategory;
import helper.JdbcHelper;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public final class GraduateProjectCategoryDao {
	private static GraduateProjectCategoryDao graduataprojectcategoryDao=
			new GraduateProjectCategoryDao();
	private GraduateProjectCategoryDao(){}
	public static GraduateProjectCategoryDao getInstance(){
		return graduataprojectcategoryDao;
	}

	public Set<GraduateProjectCategory> findAll() throws SQLException {
		Set<GraduateProjectCategory> graduateProjectCategorys = new HashSet<GraduateProjectCategory>();
		//获得连接对象
		Connection connection = JdbcHelper.getConn();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from graduateprojectcategory");
		//若结果集仍然有下一条记录，则执行循环体
		while (resultSet.next()){
			//以当前记录中的id,description,no,remarks值为参数，创建GraduateProjectCategory对象
			GraduateProjectCategory GraduateProjectCategory = new GraduateProjectCategory(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getString("no"),resultSet.getString("remarks"));
			//向graduateProjectCategorys集合中添加GraduateProjectCategory对象
			graduateProjectCategorys.add(GraduateProjectCategory);
		}
		//关闭资源
		JdbcHelper.close(resultSet,statement,connection);
		return graduateProjectCategorys;
	}
	public GraduateProjectCategory find(Integer id) throws SQLException{
		GraduateProjectCategory graduateProjectCategory = null;
		Connection connection = JdbcHelper.getConn();
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GraduateProjectCategory WHERE id=?");
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		ResultSet resultSet = preparedStatement.executeQuery();
		//由于id不能取重复值，故结果集中最多有一条记录
		//若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建GraduateProjectCategory对象
		//若结果集中没有记录，则本方法返回null
		if (resultSet.next()){
			graduateProjectCategory = new GraduateProjectCategory(resultSet.getInt("id"),
					resultSet.getString("description"),
					resultSet.getString("no"),
					resultSet.getString("remarks"));
		}
		//关闭资源
		JdbcHelper.close(resultSet,preparedStatement,connection);
		return graduateProjectCategory;
	}

	public boolean add(GraduateProjectCategory graduateProjectCategory) throws SQLException,ClassNotFoundException{
		Connection connection = JdbcHelper.getConn();
		String addgraduateProjectCategory_sql = "INSERT INTO GraduateProjectCategory (description,no,remarks) VALUES"+" (?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addgraduateProjectCategory_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,graduateProjectCategory.getDescription());
		preparedStatement.setString(2,graduateProjectCategory.getNo());
		preparedStatement.setString(3,graduateProjectCategory.getRemarks());
		//执行预编译语句，获取添加记录行数并赋值给affectedRowNum
		int affectedRowNum=preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRowNum>0;
	}

	//delete方法，根据GraduateProjectCategory的id值，删除数据库中对应的GraduateProjectCategory对象
	public boolean delete(int id) throws ClassNotFoundException,SQLException{
		Connection connection = JdbcHelper.getConn();
		//写sql语句
		String deleteGraduateProjectCategory_sql = "DELETE FROM GraduateProjectCategory WHERE id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteGraduateProjectCategory_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		//执行预编译语句，获取删除记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}

	public boolean update(GraduateProjectCategory graduateProjectCategory) throws ClassNotFoundException,SQLException{
		Connection connection = JdbcHelper.getConn();
		//写sql语句
		String updateGraduateProjectCategory_sql = " update graduateprojectcategory set description=?,no=?,remarks=? where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(updateGraduateProjectCategory_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,graduateProjectCategory.getDescription());
		preparedStatement.setString(2,graduateProjectCategory.getNo());
		preparedStatement.setString(3,graduateProjectCategory.getRemarks());
		preparedStatement.setInt(4,graduateProjectCategory.getId());
		//执行预编译语句，获取改变记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}
}
