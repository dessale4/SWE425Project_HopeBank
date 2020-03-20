package edu.mum.cs.cs425.studentproject.security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	    se.getSession().setMaxInactiveInterval(1*60);
	    System.out.println("Session starting");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		se.getSession().invalidate();
	}
}
