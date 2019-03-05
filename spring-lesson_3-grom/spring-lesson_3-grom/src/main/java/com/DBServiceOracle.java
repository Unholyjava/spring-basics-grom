package com;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.File;

public class DBServiceOracle implements DBService {

	private static final String SELECT_FILES_BY_ID_STORAGE = "SELECT * FROM FILES WHERE ID_STORAGE = :idStorage";	
	private SessionFactory sessionFactory;
	
	@Override
	public List<File> findFilesByStorageID(long idStorage) throws Exception {
		try (Session session = createSessionFactory().openSession()) {
			Query<File> query = null;
			query = session.createNativeQuery(SELECT_FILES_BY_ID_STORAGE, File.class);
			query.setParameter("idStorage", idStorage);
			return query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception("findBy " + idStorage + " of Storage is failed");
		}
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

}
