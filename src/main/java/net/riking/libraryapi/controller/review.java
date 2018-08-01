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
import net.riking.libraryapi.utils.TimesUtil;
import net.sf.json.JSONArray;

@RestController 
public class review {
	
	@RequestMapping("/reviewinfo")
	public static List reviewinfo(){
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		List<User> users=null;
		try {
			Query query = session.createQuery("from User where flag != ?");
			query.setParameter(0, 0);
			users = query.list(); 
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		

		return users;
		
	}
	
	@RequestMapping("/reviewact")
	public static Integer reviewact(@RequestParam String code){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		int i=0;
		
		try {
			Query query = session.createQuery("from User where code = ?");
			query.setParameter(0, code);
			User user = (User) query.uniqueResult();
			user.setFlag(2);
			session.save(user);
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
	
	@RequestMapping("/returnbookinfo")
	public static List returnbookinfo(){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		List<Record> records=null;
		try {
			Query query = session.createQuery("from Record where flag = ?");
			query.setParameter(0, "已申请还书");
			records = query.list(); 
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		return records;
		
	}
	
	
	@RequestMapping("/reviewreturn")
	public static Integer reviewreturn(@RequestParam Integer id){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		int i=0;
		String isbn = "";
		try {
			Query query = session.createQuery("from Record where id = ?");
			query.setParameter(0, id);
			Record record = (Record) query.uniqueResult();
			isbn = record.getBookcode();
			record.setFlag("已归还");
			session.save(record);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		try {
			Query query = session.createQuery("from Book where code = ?");
			query.setParameter(0, isbn);
			Book book = (Book) query.uniqueResult();
			Integer stock = book.getStock();
			stock++;
			book.setStock(stock);
			session.save(book);
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
	
	@RequestMapping("/borrowbookinfo")
	public static List borrowbookinfo(){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		List<Record> records=null;
		try {
			Query query = session.createQuery("from Record where flag = ?");
			query.setParameter(0, "审核中");
			records = query.list(); 
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		return records;
		
	}
	
	@RequestMapping("/reviewborrow")
	public static Integer reviewborrow(@RequestParam Integer id){
		int i =0;
		String time = TimesUtil.gettime();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("from Record where id = ?");
			query.setParameter(0, id);
			Record record = (Record) query.uniqueResult();
			record.setFlag("在借中");
			record.setBorrowtime(time);
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
	
	
	@RequestMapping("/borrowreviewno")
	public static Integer borrowreviewno(@RequestParam Integer id) {
		int i=0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		String code="";
		try {
			Query query = session.createQuery("from Record where id = ?");
			query.setParameter(0, id);
			Record record = (Record) query.uniqueResult();
			record.setFlag("审核不通过");
			code = record.getBookcode();
			session.save(record);

	
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		
		try {
			Query query = session.createQuery("from Book where code = ?");
			query.setParameter(0, code);
			Book book = (Book) query.uniqueResult();
			Integer stock = book.getStock();
			stock++;
			book.setStock(stock);
			session.save(book);
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


}
