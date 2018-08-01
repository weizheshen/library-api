package net.riking.libraryapi.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.riking.libraryapi.entity.Book;
import net.riking.libraryapi.entity.Record;
import net.riking.libraryapi.entity.User;

@RestController 
public class checkbookstock {
	
	@RequestMapping("/checkbookstock")
	public static List checkbookstock(){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		List<Book> books=null;
		try {
			Query query = session.createQuery("from Book where stock > ?");
			query.setParameter(0,0);
			books = query.list(); 
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		

		return books;
	}
	
	
	
	@RequestMapping("/checkborrowrecord")
	public static List checkborrowrecord(@RequestParam String code){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		List<Record> records=null;
		try {
			Query query = session.createQuery("from Record where bookcode = ? and flag = ?");
			query.setParameter(0,code);
			query.setParameter(1,"在借中");
			records = query.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		return records;
	}
	

}
