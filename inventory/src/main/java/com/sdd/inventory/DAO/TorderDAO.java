package com.sdd.inventory.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sdd.inventory.domain.Torder;


public class TorderDAO {
	
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session;
	
	@SuppressWarnings("unchecked")
	public List<Torder> listAll(){
		List<Torder> objList = null;
		session = sessionFactory.openSession();
		objList = session.createQuery("from Torder").list();
		session.close();
		return objList;
	}
	
	public void delete(Torder objorder) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		session.delete(objorder);
		trx.commit();
		session.close();
	}
	

	
	
}
