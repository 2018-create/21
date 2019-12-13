package service;


import dao.GraduateProjectTypeDao;
import domain.GraduateProjectType;

import java.sql.SQLException;
import java.util.Collection;

public final class GraduateProjectTypeService {
	private static GraduateProjectTypeDao graduateProjectTypeDao = GraduateProjectTypeDao.getInstance();
	private static GraduateProjectTypeService graduateProjectTypeService = new GraduateProjectTypeService();
	private GraduateProjectTypeService(){}

	public static GraduateProjectTypeService getInstance(){
		return graduateProjectTypeService;
	}

	//定义相关所需的方法(增删改查)
	public Collection<GraduateProjectType> findAll() throws SQLException {
		return graduateProjectTypeDao.findAll();
	}

	public GraduateProjectType find(Integer id) throws SQLException {
		return graduateProjectTypeDao.find(id);
	}

	public boolean update(GraduateProjectType GraduateProjectType) throws SQLException,ClassNotFoundException{
		return graduateProjectTypeDao.update(GraduateProjectType);
	}

	public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
		return graduateProjectTypeDao.delete(id);
	}


	public boolean add(GraduateProjectType GraduateProjectType) throws SQLException, ClassNotFoundException {
		return graduateProjectTypeDao.add(GraduateProjectType);
	}


}
