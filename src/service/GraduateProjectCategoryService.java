package service;

import dao.GraduateProjectCategoryDao;
import domain.GraduateProjectCategory;

import java.sql.SQLException;
import java.util.Collection;

public final class GraduateProjectCategoryService {
	private static GraduateProjectCategoryDao graduateProjectCategoryDao = GraduateProjectCategoryDao.getInstance();
	private static GraduateProjectCategoryService graduateProjectCategoryService = new GraduateProjectCategoryService();
	private GraduateProjectCategoryService(){}

	public static GraduateProjectCategoryService getInstance(){
		return graduateProjectCategoryService;
	}
	//定义相关所需的方法(增删改查)
	public Collection<GraduateProjectCategory> findAll() throws SQLException {
		return graduateProjectCategoryDao.findAll();
	}
	public GraduateProjectCategory find(Integer id) throws SQLException {
		return graduateProjectCategoryDao.find(id);
	}
	public boolean update(GraduateProjectCategory GraduateProjectCategory) throws SQLException,ClassNotFoundException{
		return graduateProjectCategoryDao.update(GraduateProjectCategory);
	}
	public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
		return graduateProjectCategoryDao.delete(id);
	}
	public boolean add(GraduateProjectCategory GraduateProjectCategory) throws SQLException, ClassNotFoundException {
		return graduateProjectCategoryDao.add(GraduateProjectCategory);
	}
}
