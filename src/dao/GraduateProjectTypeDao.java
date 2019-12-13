package dao;


import domain.GraduateProjectType;
import helper.JdbcHelper;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public final class GraduateProjectTypeDao {
	private static GraduateProjectTypeDao graduateProjectTypeDao=
			new GraduateProjectTypeDao();
	private GraduateProjectTypeDao(){}
	public static GraduateProjectTypeDao getInstance(){
		return graduateProjectTypeDao;
	}

	public Set<GraduateProjectType> findAll() throws SQLException {
		Set<GraduateProjectType> graduateProjectTypes = new HashSet<GraduateProjectType>();
		//获得连接对象
		Connection connection = JdbcHelper.getConn();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from graduateprojecttype");
		//若结果集仍然有下一条记录，则执行循环体
		while (resultSet.next()){
			//以当前记录中的id,description,no,remarks值为参数，创建GraduateProjectType对象
			GraduateProjectType graduateProjectType = new GraduateProjectType(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getString("no"),resultSet.getString("remarks"));
			//向GraduateProjectTypes集合中添加GraduateProjectType对象
			graduateProjectTypes.add(graduateProjectType);
		}
		//关闭资源
		JdbcHelper.close(resultSet,statement,connection);
		return graduateProjectTypes;
	}

	public GraduateProjectType find(Integer id) throws SQLException{
		GraduateProjectType graduateProjectType = null;
		Connection connection = JdbcHelper.getConn();
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM graduateprojecttype WHERE id=?");
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		ResultSet resultSet = preparedStatement.executeQuery();
		//由于id不能取重复值，故结果集中最多有一条记录
		//若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建GraduateProjectType对象
		//若结果集中没有记录，则本方法返回null
		if (resultSet.next()){
			graduateProjectType = new GraduateProjectType(resultSet.getInt("id"),
					resultSet.getString("description"),
					resultSet.getString("no"),
					resultSet.getString("remarks"));
		}
		//关闭资源
		JdbcHelper.close(resultSet,preparedStatement,connection);
		return graduateProjectType;
	}

	public boolean add(GraduateProjectType graduateProjectType) throws SQLException,ClassNotFoundException{
		Connection connection = JdbcHelper.getConn();
		String addGraduateProjectType_sql = "INSERT INTO graduateprojecttype (description,no,remarks) VALUES"+" (?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addGraduateProjectType_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,graduateProjectType.getDescription());
		preparedStatement.setString(2,graduateProjectType.getNo());
		preparedStatement.setString(3,graduateProjectType.getRemarks());
		//执行预编译语句，获取添加记录行数并赋值给affectedRowNum
		int affectedRowNum=preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRowNum>0;
	}


	//delete方法，根据GraduateProjectType的id值，删除数据库中对应的GraduateProjectType对象
	public boolean delete(int id) throws ClassNotFoundException,SQLException{
		Connection connection = JdbcHelper.getConn();
		//写sql语句
		String deleteGraduateProjectType_sql = "DELETE FROM graduateprojecttype WHERE id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteGraduateProjectType_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		//执行预编译语句，获取删除记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}

	public boolean update(GraduateProjectType graduateProjectType) throws ClassNotFoundException,SQLException{
		Connection connection = JdbcHelper.getConn();
		//写sql语句
		String updateGraduateProjectType_sql = " update graduateprojecttype set description=?,no=?,remarks=? where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(updateGraduateProjectType_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,graduateProjectType.getDescription());
		preparedStatement.setString(2,graduateProjectType.getNo());
		preparedStatement.setString(3,graduateProjectType.getRemarks());
		preparedStatement.setInt(4,graduateProjectType.getId());
		//执行预编译语句，获取改变记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}
}
