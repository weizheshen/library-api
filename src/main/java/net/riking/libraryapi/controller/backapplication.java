package net.riking.libraryapi.controller;

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
public class backapplication {
	@RequestMapping("/backreturn")
	public static Integer backreturn(@RequestParam Integer id) {
		int i=0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("from Record where id = ?");
			query.setParameter(0, id);
			Record record = (Record) query.uniqueResult();
			record.setFlag("在借中");
			session.save(record);
			i=1;
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		return i;
	}
	
	@RequestMapping("/backborrow")
	public static Integer backborrow(@RequestParam Integer id) {
		int i = 0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		try {
			
			//返还库存
			Query query = session.createQuery("from Record where id = ?");
			query.setParameter(0, id);
			Record record = (Record) query.uniqueResult();
			String code = record.getBookcode();
			Query query1 = session.createQuery("from Book where code = ?");
			query1.setParameter(0, code);
			Book book = (Book) query1.uniqueResult();
			int stock = book.getStock();
			stock++;
			book.setStock(stock);
			session.save(book);
			delrecord(id);

			i=1;
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
			System.out.println("bbb");
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		return i;
	}
	
	
	
	//删除记录
	public static void delrecord(Integer id) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		try {
			
			Record record = new Record();
			record.setId(id);
			session.delete(record);
	  

		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		
	}
	
	


}
