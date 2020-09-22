package com.isika.cdi5.tp.banque.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("tp-banque");
	}
	
	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	public static void closeAll() {
		if(entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}
}
