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
public class modifybookinfo {
	
	@RequestMapping("/modifybookinfo")
	public static Integer modifybookinfo(@RequestParam String code,@RequestParam String booktitle,@RequestParam String author,@RequestParam String publish,@RequestParam Integer stock){
		int i=0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("from Book where code = ?");
			query.setParameter(0, code);
			Book book = (Book) query.uniqueResult();
			if(booktitle.equals("undefined")){
				booktitle = book.getBooktitle();
			}
			if(author.equals("undefined")){
				author = book.getAuthor();
			}
			if(publish.equals("undefined")){
				publish = book.getPublish();
			}
			book.setBooktitle(booktitle);
			book.setAuthor(author);
			book.setPublish(publish);
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
