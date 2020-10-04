package edu.mum.cs.cs425.studentproject.security;

import java.time.LocalDateTime;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener{
	@Override
	public void sessionCreated(HttpSessionEvent se) {
	    se.getSession().setMaxInactiveInterval(15);
	    System.out.println("Session startingat " + LocalDateTime.now());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		System.out.println("Session destroyed at " + LocalDateTime.now() );
		se.getSession().invalidate();
		System.out.println("Session invalidated at " + LocalDateTime.now());
	}
}
