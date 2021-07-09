package com.project.chat.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionConfig implements HttpSessionListener{

	public static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();
	
    public synchronized static String getSessionidCheck(String type, String compareId){
    	 String result = "";
    	 
    	 for(String key : sessions.keySet()) {
    		 HttpSession value = sessions.get(key);
    		 if(value != null && value.getAttribute(type) != null && value.getAttribute(type).toString().equals(compareId)) {
    			 result = key;
    			 removeSessionForDoubleLogin(result);
    		 }
    	 }
    	 
    	 return result;
    }
    
    private static void removeSessionForDoubleLogin(String userId){
       
    	if(userId !=null && userId.length() > 0) {
    		sessions.get(userId).invalidate();
    		sessions.remove(userId);
    	}
    }
    
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub 
		System.out.println(se);
		sessions.put(se.getSession().getId(), se.getSession());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println(se); 
		if(sessions.get(se.getSession().getId()) != null) {
			sessions.get(se.getSession().getId()).invalidate();
			sessions.remove(se.getSession().getId()); 
		}
	}
	

}
