package com.cubic.CrudHibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HiberanteCrud {

	public static void insert() {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Employee e=new Employee();
		e.setEname("reddy");
		e.setEdept("JAVA");
		e.setEmail("reddy@gmail.com");
		e.setEsal("20000");
		s.persist(e);
		t.commit();
		s.close();
		sf.close();
	}

	public static void fetch() {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Query<Employee> q=s.createQuery("from Employee where ename=:name");
		q.setString("name", "abel");
		q.uniqueResult();
		List<Employee> l=q.list();
		Iterator<Employee> itr=l.iterator();
		while(itr.hasNext()) {
			Employee e=(Employee)itr.next();
			System.out.println(e.getEid()+"   "+e.getEname()+"    "+e.getEdept()+"   "+e.getEmail()+"   "+e.getEsal());
		}
		t.commit();
		s.close();
		sf.close();
	}

	public static void update() {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		int eid=1;
		Employee e=(Employee)s.get(Employee.class, eid);
		e.setEdept("Testing");
		s.update(e);
		System.out.println("Successfully Updated");	
	}
}
