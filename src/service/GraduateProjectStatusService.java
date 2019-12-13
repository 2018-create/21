package service;


import dao.GraduateProjectStatusDao;
import domain.GraduateProjectStatus;

import java.sql.SQLException;
import java.util.Collection;

public final class GraduateProjectStatusService {
	private static GraduateProjectStatusDao graduateProjectStatusDao = GraduateProjectStatusDao.getInstance();
	private static GraduateProjectStatusService graduateProjectStatusService = new GraduateProjectStatusService();
	private GraduateProjectStatusService(){}

	public static GraduateProjectStatusService getInstance(){
		return graduateProjectStatusService;
	}

	//定义相关所需的方法(增删改查)
	public Collection<GraduateProjectStatus> findAll() throws SQLException {
		return graduateProjectStatusDao.findAll();
	}

	public GraduateProjectStatus find(Integer id) throws SQLException {
		return graduateProjectStatusDao.find(id);
	}

	public boolean update(GraduateProjectStatus GraduateProjectStatus) throws SQLException,ClassNotFoundException{
		return graduateProjectStatusDao.update(GraduateProjectStatus);
	}

	public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
		return graduateProjectStatusDao.delete(id);
	}


	public boolean add(GraduateProjectStatus GraduateProjectStatus) throws SQLException, ClassNotFoundException {
		return graduateProjectStatusDao.add(GraduateProjectStatus);
	}


}
