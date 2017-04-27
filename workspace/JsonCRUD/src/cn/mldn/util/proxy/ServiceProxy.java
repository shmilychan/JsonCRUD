package cn.mldn.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.mldn.util.dbc.DatabaseConnection;

public class ServiceProxy implements InvocationHandler {
	private Object target = null;
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object retObject = null ;	// 接收返回对象
		if (method.getName().startsWith("add")
				|| method.getName().startsWith("edit")
				|| method.getName().startsWith("delete")) {
			DatabaseConnection.getConnection().setAutoCommit(false);
			try {
				retObject = method.invoke(this.target, args) ;
				DatabaseConnection.getConnection().commit(); 
			} catch (Exception e) {
				DatabaseConnection.getConnection().rollback(); 
			} finally {
				DatabaseConnection.close(); 
			}
		} else {
			try {
				retObject = method.invoke(this.target, args) ;
			} catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				DatabaseConnection.close(); 
			}
		}
		return retObject;
	}

}
