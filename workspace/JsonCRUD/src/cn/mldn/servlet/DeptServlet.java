package cn.mldn.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.service.IDeptService;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Dept;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@SuppressWarnings("serial")
@WebServlet("/DeptServlet/*")
public class DeptServlet extends HttpServlet {

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------------部门信息增加---------------------");
		Dept vo = new Dept() ;
		vo.setDname(request.getParameter("dname"));
		IDeptService deptService = Factory.getServiceInstance("dept.service");
		try {
			deptService.add(vo) ;
			response.getWriter().println(vo.getDeptno());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------------部门信息修改---------------------");
		IDeptService deptService = Factory.getServiceInstance("dept.service");
		Dept vo = new Dept() ;
		vo.setDeptno(Integer.parseInt(request.getParameter("deptno")));
		vo.setDname(request.getParameter("dname"));
		try {
			response.getWriter().println(deptService.edit(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------------部门信息列表---------------------");
		IDeptService deptService = Factory.getServiceInstance("dept.service");
		try {
			Iterator<Dept> iter = deptService.list().iterator();
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			while (iter.hasNext()) {
				jsonArray.add(iter.next());
			}
			jsonObject.put("allDepts", jsonArray);
			response.getWriter().println(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------------部门信息删除---------------------");
		System.out.println("*** 【提示信息】部门删除。...");
		int deptno = Integer.parseInt(request.getParameter("deptno")) ;
		IDeptService deptService = Factory.getServiceInstance("dept.service");
		try {
			response.getWriter().println(deptService.delete(deptno));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();//获取项目路径（/JsonCRUD/...）
		String methodName = uri.substring(uri.lastIndexOf("/") + 1);//截取最后一个
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,request,response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
