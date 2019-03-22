package com;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.File;
import entity.Storage;

public class StorageDAO extends CommonDAO<Storage>{
	
	private static final String SELECT_FILES_BY_ID_STORAGE = "SELECT * FROM FILES WHERE ID_STORAGE = :idStorage";	
	private SessionFactory sessionFactory;

	public StorageDAO(Class<Storage> classCurrent) {
		super(classCurrent);
	}
	
	public Storage save(Storage storage) throws Exception {
		if (findById(storage.getId()) != null) {
			throw new Exception("Storage with id = " + storage.getId() + " exists, no save Storage");
		}
		return super.save(storage);
	}

	public Storage delete(long id) throws Exception {
		return super.delete(id);
	}
	
	public Storage update(Storage storage) throws Exception {
		Storage storageOld = findById(storage.getId());
		if (storageOld == null) {
			throw new Exception("Storage with id = " + storage.getId() + " is upsent, no update Storage");
		}	
		return super.update(storage);
	}
	
	public Storage findById(long id) throws Exception {
		return super.findById(id);
	}
	
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
