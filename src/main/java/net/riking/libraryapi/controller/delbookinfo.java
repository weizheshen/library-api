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

@RestController
public class delbookinfo {
	
	@RequestMapping("/delbookinfo")
	public static Integer delbookinfo(@RequestParam Integer id){
		int i=0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		try {
			Book book = new Book();
			book.setId(id);
			session.delete(book);
			
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
