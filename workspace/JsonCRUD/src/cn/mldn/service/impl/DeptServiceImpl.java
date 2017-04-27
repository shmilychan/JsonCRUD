package cn.mldn.service.impl;

import java.util.List;

import cn.mldn.dao.IDeptDAO;
import cn.mldn.service.IDeptService;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Dept;

public class DeptServiceImpl implements IDeptService {

	@Override
	public List<Dept> list() throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		return deptDAO.findAll();
	}

	@Override
	public boolean add(Dept vo) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		if (deptDAO.doCreate(vo)) {	// 保存成功
			vo.setDeptno(deptDAO.findAutoId()); 	// 追加部门编号
			return true ;
		}
		vo.setDeptno(0); 	// 如果是0表示增加失败
		return false ; 
	}

	@Override
	public boolean edit(Dept vo) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		return deptDAO.doUpdate(vo);
	}
	@Override
	public boolean delete(int deptno) throws Exception {
		IDeptDAO deptDAO = Factory.getDAOInstance("dept.dao") ;
		return deptDAO.doRemove(deptno); 
	}

}
