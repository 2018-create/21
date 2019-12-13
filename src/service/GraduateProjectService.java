package service;


import dao.GraduateProjectDao;
import domain.GraduateProject;

import java.sql.SQLException;
import java.util.Collection;

public final class GraduateProjectService {
	private static GraduateProjectDao graduateProjectDao = GraduateProjectDao.getInstance();
	private static GraduateProjectService graduateProjectService = new GraduateProjectService();
	private GraduateProjectService(){}

	public static GraduateProjectService getInstance(){
		return graduateProjectService;
	}

	//定义相关所需的方法(增删改查)
	public Collection<GraduateProject> findAll() throws SQLException {
		return graduateProjectDao.findAll();
	}

	public GraduateProject find(Integer id) throws SQLException {
		return graduateProjectDao.find(id);
	}

	public boolean update(GraduateProject GraduateProject) throws SQLException,ClassNotFoundException{
		return graduateProjectDao.update(GraduateProject);
	}

	public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
		return graduateProjectDao.delete(id);
	}


	public boolean add(GraduateProject GraduateProject) throws SQLException, ClassNotFoundException {
		return graduateProjectDao.add(GraduateProject);
	}


}
