package net.riking.libraryapi.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.riking.libraryapi.entity.Book;

@RestController 
public class allbooks {
	
	
	@RequestMapping("/allbooks")
	public static List allbooks(){
		int i=0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		List<Book> books=null;
		try {
			Query query = session.createQuery("from Book");
			books = query.list();
			i=1;

			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		return books;
	}
//	public static void main(String[] args) {
//		allbooks();
//	}

}
