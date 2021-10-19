package com.sdd.inventory.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.sdd.inventory.domain.Muser;



public class MuserDAO {
	Muser objuser;
	
	public Muser authentication(String username) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Muser objuser = (Muser) session.createQuery("from Muser where username = '"+username+"'").uniqueResult();
		session.close();
		return objuser;
	}

	@SuppressWarnings("unchecked")
	public List<Muser> listAll() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		List<Muser> objList = session.createQuery("from Muser").list();
		session.close();
		return objList;
	}
	
	
}
