package com;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.File;
import entity.Storage;

public class FileDAO extends CommonDAO<File>{

	public FileDAO(Class<File> classCurrent) {
		super(classCurrent);
	}
	
	public File save(File file) throws Exception {
		if (findById(file.getId()) != null) {
			throw new Exception("File with id = " + file.getId() + " exists, no save File");
		}
		if (file.getName().length() > 10) {
			throw new Exception("Too much lenght of File's name, ID = " + file.getId()+ " , no save File");
		}
		return super.save(file);
	}

	public File delete(long id) throws Exception {
		return super.delete(id);
	}
	
	public File update(File file) throws Exception {
		File fileOld = findById(file.getId());
		if (fileOld == null) {
			throw new Exception("File with id = " + file.getId() + " is upsent, no update File");
		}	
		return super.update(file);
	}
	
	public File findById(long id) throws Exception {
		return super.findById(id);
	}
	
	public void updateFileArrayByStorage(Storage storageTo, List<File> fileList) throws Exception {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			for (File file : fileList) {
				file.setStorage(storageTo);
				update(file);
			}
					
			transaction.commit();
			System.out.println("Update of File's array is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			//e.printStackTrace();
			throw new Exception("Update of File's array is failed");
		} 
	}
}
