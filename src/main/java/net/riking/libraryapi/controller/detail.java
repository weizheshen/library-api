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

@RestController
public class detail {
	
	@RequestMapping("/detail")
	public static Integer detail(@RequestParam Integer id){
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		int i=0;
		String flag = "";
		try {
			Query query = session.createQuery("from Record where id = ?");
			query.setParameter(0, id);
			Record record = (Record) query.uniqueResult();
			flag = record.getFlag();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		if(flag.equals("审核中")){
			i=1;
		}else if(flag.equals("已归还")){
			i=2;
		}else if(flag.equals("在借中") ){
			i=3;
		}else if(flag.equals("已申请还书")){
			i=4;
		}else if(flag.equals("审核不通过")) {
			i=5;
		}else{
			i=0;
		}
		
		
		return i;
	}
}
