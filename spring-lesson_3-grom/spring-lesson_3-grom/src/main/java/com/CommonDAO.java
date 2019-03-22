package com;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CommonDAO<T> implements DAO<T>{
	private SessionFactory sessionFactory;
	private final Class<T> classCurrent;
	
	public CommonDAO (Class<T> classCurrent) {
		this.classCurrent = classCurrent;
	}
		
	public T save(T entity) throws Exception {
		
		Transaction transaction = null;
		String entityClass = entity.getClass().getSimpleName();
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(entity);
			
			transaction.commit();
			System.out.println("Save " + entityClass + " is done");
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("Save " + entityClass + " is failed");
		}
		return entity;
	}
	
	public T delete(long id) throws Exception {
		Transaction transaction = null;
		T entity = findById(id);
		if (entity == null) {
			throw new Exception("Not found by ID = " + id);
		}
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.delete(entity);
			
			transaction.commit();
			System.out.println("Delete Item is done, ID = " + id);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("Delete Item is failed, ID = " + id);
		} 
		return entity;
	}
	
	public T update(T entity) throws Exception {
		Transaction transaction = null;
		String entityClass = entity.getClass().getSimpleName();
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
						
			session.update(entity);
					
			transaction.commit();
			System.out.println("Update " + entityClass + " is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new Exception("Update " + entityClass + " is failed");
		} 
		return entity;
	}
	
	public T findById(long id) throws Exception {
		try (Session session = createSessionFactory().openSession()) {
			return session.get(classCurrent, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("findById of Item is failed, ID = " + id);
		}
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
}
