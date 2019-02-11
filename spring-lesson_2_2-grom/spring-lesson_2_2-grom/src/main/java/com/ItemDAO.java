package com;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import item.*;

public class ItemDAO {
	private SessionFactory sessionFactory; 
		
	public Item save(Item entity) throws Exception {
		if (entity == null || entity.getName() == null) {
			throw new Exception("Item without name, no save Item");
		}
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			entity.setDateCreated(new Date());
			session.save(entity);
			
			transaction.commit();
			System.out.println("Save Item is done");
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("Save Item is failed");
		}
		return entity;
	}
	
	
	public Item delete(long id) throws Exception {
		Transaction transaction = null;
		Item entity = findById(id);
		if (entity == null) {
			System.out.println("Not found Item by ID = " + id);
			return null;
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
	
	public Item update(Item entity) throws Exception {
		Item entityOld = findById(entity.getId());
		if (entityOld == null) {
			throw new Exception("Item is upsent, no update Item");
		}	
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			Date dateCreate = entityOld.getDateCreated();
			entity.setDateCreated(dateCreate);
			entity.setLastUpdatedDate(new Date());
			session.update(entity);
					
			transaction.commit();
			System.out.println("Update Item is done, ID = " + entity.getId());
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new Exception("Update Item is failed, ID = " + entity.getId());
		} 
		return entity;
	}
	
	public Item findById(long id) throws Exception {
		try (Session session = createSessionFactory().openSession()) {
			return session.get(Item.class, id);
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
