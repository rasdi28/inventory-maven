package com.sdd.inventory.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sdd.inventory.domain.Mproduct;



public class MproductDAO {

	SessionFactory sesionFactory = new Configuration().configure().buildSessionFactory();
	Session session;
	
	@SuppressWarnings("unchecked")
	public List<Mproduct> listAll() {
		List<Mproduct> objList = null;
		session = sesionFactory.openSession();
		objList = session.createQuery("from Mproduct").list();
		session.close();
		return objList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Mproduct> getByProductname(String productname) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		List<Mproduct> objList = session
				.createSQLQuery("select * from Mproduct where productname like '%" + productname.toUpperCase() + "%'")
				.addEntity(Mproduct.class).list();
		session.close();
		return objList;
	}
	
	public void save(Mproduct objproduct) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		session.saveOrUpdate(objproduct);
		trx.commit();
		session.close();
	}
	public void delete(Mproduct objproduct) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction trx = session.beginTransaction();
		session.delete(objproduct);
		trx.commit();
		session.close();
	}
	
}
