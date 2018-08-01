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

import net.riking.libraryapi.entity.Record;

@RestController 
public class checkrecord {
	
	@RequestMapping("/checkrecord")
	public static List checkrecord(@RequestParam String logincode){
		String openid = request.requestopenid(logincode);
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		List<Record> record=null;
		try {
			Query query = session.createQuery("from Record where useropenid = ?");
			query.setParameter(0,openid);
			record = query.list(); 
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		return record;
		
	}

}
