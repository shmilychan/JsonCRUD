package cn.mldn.util.factory;

import cn.mldn.util.MessageUtil;
import cn.mldn.util.proxy.ServiceProxy;

public class Factory {
	private final static MessageUtil DAO_MESSAGE = new MessageUtil("cn.mldn.util.message.dao") ;
	private final static MessageUtil SERVICE_MESSAGE = new MessageUtil("cn.mldn.util.message.service") ;
	@SuppressWarnings("unchecked")
	public static <T> T getDAOInstance(String daoName) {
		String className = DAO_MESSAGE.getText(daoName)  ;
		T t = null ;
		try {
			t = (T) Class.forName(className).newInstance() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t ;
	} 
	@SuppressWarnings("unchecked")
	public static <T> T getServiceInstance(String serviceName) {
		String className = SERVICE_MESSAGE.getText(serviceName) ;
		T t = null ;
		try {
			t = (T) new ServiceProxy().bind(Class.forName(className).newInstance()) ;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return t ; 
	}
}
