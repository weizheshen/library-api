package net.riking.libraryapi.controller;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.riking.libraryapi.entity.Book;
import net.riking.libraryapi.utils.HttpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class addbookinfo {
	
	@RequestMapping("/addbookinfo")
	public static Integer addbookinfo(@RequestParam String code) {  

		int i=0;
        String Publish = null;
        String Name = null;
        String Author = null;
        String Picture = null; 
        String pubdate = null;
        String summary = null;
        String author_intro = null;
        String numraters=null;
        String catalog=null;
        String average=null;
        try {  
           
            String result=HttpUtil.get("https://api.douban.com/v2/book/isbn/:"+code, "");  
            //将返回字符串转换为JSON对象  
            JSONObject json=JSONObject.fromObject(result);  
            //得到出版社  
            Publish=json.get("publisher").toString();
            //System.out.println(Publish);
            //得到书名  
            Name=json.get("title").toString();
            //Name = Gbk2Utf.getUTF8StringFromGBKString(Name);
            
            //System.out.println(Name);
            //得到作者，因为得到的是数组，所以要转化  
            JSONArray arrAuthor=JSONArray.fromObject(json.get("author"));  
            Author=arrAuthor.getString(0).toString(); 
            //System.out.println(Author);
            //得到目录  
            catalog=json.get("catalog").toString();
            //双层解析获得评分和评分人数
            String rating=json.get("rating").toString();
           //将返回字符串转换为JSON对象  
            JSONObject js=JSONObject.fromObject(rating);
            average = js.get("average").toString();
            numraters = js.get("numRaters").toString();
     
            //得到封面
            Picture=json.get("image").toString();
            //System.out.println(Picture);
            //出版日期
            pubdate=json.get("pubdate").toString();
            //简介
            summary=json.get("summary").toString();
            //作者简介
            author_intro=json.get("author_intro").toString();
           

        } catch (Exception e) {  
            JOptionPane.showMessageDialog(null, "网络连接失败···");  
        }
        
        SessionFactory factory = new Configuration().configure().buildSessionFactory();//加载配置
		Session session=factory.openSession();   //创建会话
		Transaction tx=session.beginTransaction();
		Integer stock = 0;
		try {
			Query query = session.createQuery("from Book where code = ?");
			query.setParameter(0, code);
			Book book = (Book) query.uniqueResult();
			stock = book.getStock();
			stock++;
			book.setStock(stock);
			session.save(book);
			i=1;	
		} catch (Exception e) {
			Book book = new Book();
			book.setBooktitle(Name);
			book.setAuthor(Author);
			book.setCode(code);
			book.setPublish(Publish);
			book.setPicture(Picture);
			book.setPubdate(pubdate);
			book.setSummary(summary);
			book.setAuthor_intro(author_intro);
			book.setCatalog(catalog);
			book.setAverage(average);
			book.setNumraters(numraters);
			book.setStock(1);
			session.save(book);
			i=1;
			
		}
		
		session.getTransaction().commit();//提交事务
		session.close();
		factory.close();
        
        return i;  

		}
	
//	public static void main(String[] args) {
//		Integer s=addbookinfo("9787121313318");
//		System.out.println(s);
//	}
}


