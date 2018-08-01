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
import net.riking.libraryapi.utils.TimesUtil;

@RestController 
public class borrowbook {
	
	
	@RequestMapping("/borrowbook")
	public static Integer borrowbook(@RequestParam String code,@RequestParam String logincode){
		Integer i=0;
		
		String openid = request.requestopenid(logincode);
		String time = TimesUtil.gettime();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		String name=null;
		Integer stock = 0;
		try {
			Query query = session.createQuery("from User where code = ?");
			query.setParameter(0, openid);
			User user = (User) query.uniqueResult();
			name=user.getUsername();	
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		
		
		try {
			Query query = session.createQuery("from Book where code = ?");
			query.setParameter(0, code);
			Book book = (Book) query.uniqueResult();
			stock = book.getStock();
			if(stock>0){
				stock--;
				book.setStock(stock);
				String bookname = book.getBooktitle();
				String picture = book.getPicture();
				String bookcode = book.getCode();
				Record record = new Record();
				record.setPicture(picture);
				record.setBookname(bookname);
				record.setUsername(name);
				record.setBorrowtime(time);
				record.setBookcode(bookcode);
				record.setFlag("在借中");
				
				record.setUseropenid(openid);
				session.save(record);
				session.save(book);
				i=1;
			}	
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		
		return i; //返回操作反馈
	}
	

}
