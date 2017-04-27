package cn.mldn.dao;

import java.sql.SQLException;

import cn.mldn.util.dao.IBaseDAO;
import cn.mldn.vo.Dept;

public interface IDeptDAO extends IBaseDAO<Integer, Dept> {
	public Integer findAutoId() throws SQLException ;
}
