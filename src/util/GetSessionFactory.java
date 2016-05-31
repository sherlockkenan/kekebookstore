package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class GetSessionFactory {
	public static Session getSession(){
		Configuration cfg=new Configuration().configure();
		SessionFactory sfFactory=cfg.buildSessionFactory();
		Session session=sfFactory.openSession();
		return session;
	}

}
