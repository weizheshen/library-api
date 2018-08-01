package net.riking.libraryapi.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.riking.libraryapi.entity.User;

@RestController 
public class adduserinfo {
	@RequestMapping("/adduserinfo")
	public static Integer addinfo(@RequestParam String username,@RequestParam String tel,@RequestParam String number,@RequestParam String place,@RequestParam String logincode){
		Integer i=0;
		String openid=request.requestopenid(logincode);
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		
		try {
			Query query = session.createQuery("from User where code = ?");
			query.setParameter(0, openid);
			User user = (User) query.uniqueResult();
			user.setUsername(username);
			user.setTel(tel);
			user.setNumber(number);
			user.setPlace(place);
			user.setFlag(1);
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
	

}
