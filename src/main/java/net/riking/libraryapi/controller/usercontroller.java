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
import net.riking.libraryapi.entity.User;

@RestController 
public class usercontroller {
	@RequestMapping("/getusertype")
	public static Integer usertype(@RequestParam String logincode){
		Integer usertype = 0;
		String openid = "";
		openid=request.requestopenid(logincode);
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("from User where code = ?");
			query.setParameter(0, openid);
			User user = (User) query.uniqueResult();
			usertype=user.getFlag();
			
		} catch (Exception e) {
			// TODO: handle exception
//			e.printStackTrace();
//			session.getTransaction().rollback();//回滚事务
			User user=new User();
			user.setCode(openid);
			user.setFlag(0);
			session.save(user);
			usertype = 0;
		}
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		
		return usertype;
	}
	
	@RequestMapping("/getuserinfo")
	public static User getuserinfo(@RequestParam String code){
		User user = new User();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("from User where code = ?");
			query.setParameter(0, code);
			user = (User) query.uniqueResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();//回滚事务
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
		return user;
		
	}
	@RequestMapping("/modifyuserinfo")
	public static Integer modifyuserinfo(@RequestParam String code,@RequestParam String name,@RequestParam String tel,@RequestParam String number,@RequestParam String place){
		int i=0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();  //加载配置文件
		Session session=factory.openSession();    //创建会话
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("from User where code = ?");
			query.setParameter(0, code);
			User user = (User) query.uniqueResult();
			if(name.equals("undefined")){
				name = user.getUsername();
			}
			if(tel.equals("undefined")){
				tel = user.getTel();
			}
			if(number.equals("undefined")){
				number = user.getNumber();
			}
			if(place.equals("undefined")){
				place = user.getPlace();
			}

			user.setUsername(name);
			user.setTel(tel);
			user.setNumber(number);
			user.setPlace(place);
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
	
	
	@RequestMapping("/deluserinfo")
	public static Integer deluserinfo(@RequestParam Integer id){
		int i=0;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		try {
			User user = new User();
			user.setId(id);
			session.delete(user);
			
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
