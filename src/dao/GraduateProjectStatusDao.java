package dao;


import domain.GraduateProjectStatus;
import helper.JdbcHelper;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public final class GraduateProjectStatusDao {
	private static GraduateProjectStatusDao graduateProjectStatusDao=
			new GraduateProjectStatusDao();
	private GraduateProjectStatusDao(){}
	public static GraduateProjectStatusDao getInstance(){
		return graduateProjectStatusDao;
	}

	public Set<GraduateProjectStatus> findAll() throws SQLException {
		Set<GraduateProjectStatus> graduateProjectStatuss = new HashSet<GraduateProjectStatus>();
		//获得连接对象
		Connection connection = JdbcHelper.getConn();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from graduateprojectstatus");
		//若结果集仍然有下一条记录，则执行循环体
		while (resultSet.next()){
			//以当前记录中的id,description,no,remarks值为参数，创建GraduateProjectStatus对象
			GraduateProjectStatus graduateProjectStatus = new GraduateProjectStatus(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getString("no"),resultSet.getString("remarks"));
			//向GraduateProjectStatuss集合中添加GraduateProjectStatus对象
			graduateProjectStatuss.add(graduateProjectStatus);
		}
		//关闭资源
		JdbcHelper.close(resultSet,statement,connection);
		return graduateProjectStatuss;
	}

	public GraduateProjectStatus find(Integer id) throws SQLException{
		GraduateProjectStatus graduateProjectStatus = null;
		Connection connection = JdbcHelper.getConn();
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM graduateprojectstatus WHERE id=?");
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		ResultSet resultSet = preparedStatement.executeQuery();
		//由于id不能取重复值，故结果集中最多有一条记录
		//若结果集有一条记录，则以当前记录中的id,description,no,remarks值为参数，创建GraduateProjectStatus对象
		//若结果集中没有记录，则本方法返回null
		if (resultSet.next()){
			graduateProjectStatus = new GraduateProjectStatus(resultSet.getInt("id"),
					resultSet.getString("description"),
					resultSet.getString("no"),
					resultSet.getString("remarks"));
		}
		//关闭资源
		JdbcHelper.close(resultSet,preparedStatement,connection);
		return graduateProjectStatus;
	}

	public boolean add(GraduateProjectStatus graduateProjectStatus) throws SQLException,ClassNotFoundException{
		Connection connection = JdbcHelper.getConn();
		String addGraduateProjectStatus_sql = "INSERT INTO graduateprojectstatus (description,no,remarks) VALUES"+" (?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addGraduateProjectStatus_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,graduateProjectStatus.getDescription());
		preparedStatement.setString(2,graduateProjectStatus.getNo());
		preparedStatement.setString(3,graduateProjectStatus.getRemarks());
		//执行预编译语句，获取添加记录行数并赋值给affectedRowNum
		int affectedRowNum=preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRowNum>0;
	}


	//delete方法，根据GraduateProjectStatus的id值，删除数据库中对应的GraduateProjectStatus对象
	public boolean delete(int id) throws ClassNotFoundException,SQLException{
		Connection connection = JdbcHelper.getConn();
		//写sql语句
		String deleteGraduateProjectStatus_sql = "DELETE FROM graduateprojectstatus WHERE id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteGraduateProjectStatus_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		//执行预编译语句，获取删除记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}

	public boolean update(GraduateProjectStatus graduateProjectStatus) throws ClassNotFoundException,SQLException{
		Connection connection = JdbcHelper.getConn();
		//写sql语句
		String updateGraduateProjectStatus_sql = " update GraduateProjectStatus set description=?,no=?,remarks=? where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(updateGraduateProjectStatus_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,graduateProjectStatus.getDescription());
		preparedStatement.setString(2,graduateProjectStatus.getNo());
		preparedStatement.setString(3,graduateProjectStatus.getRemarks());
		preparedStatement.setInt(4,graduateProjectStatus.getId());
		//执行预编译语句，获取改变记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}
}
