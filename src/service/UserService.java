package service;

import dao.UserDao;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public final class UserService {
	private UserDao userDao = UserDao.getInstance();
	private static UserService userService = new UserService();
	
	public UserService() {
	}
	
	public static UserService getInstance(){
		return UserService.userService;
	}
    //定义相关所需的方法(增删改查，登录)
    public User find(Integer id) throws SQLException {
        return userDao.find(id);
    }
    public boolean add(User user, Connection connection) throws SQLException {
        return userDao.add(user,connection);
    }
    public User login(String username,String password) throws SQLException{
        return userDao.login(username,password);
    }
    public boolean delete(Integer id) throws SQLException {
        return userDao.delete(id);
    }
}
