package net.riking.libraryapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_record")
public class Record {
	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String useropenid;  //借书人openid
	private String username;   //借书人姓名
	private String bookname;    //书名
	private String bookcode;	//书isbn
	private String borrowtime;  //借书时间

	private String flag;        //借还状态
	private String picture;    //书籍封面
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="useropenid",length=50)
	public String getUseropenid() {
		return useropenid;
	}
	
	public void setUseropenid(String useropenid) {
		this.useropenid = useropenid;
	}
	
	@Column(name="borrowtime",length=50)
	public String getBorrowtime() {
		return borrowtime;
	}
	public void setBorrowtime(String borrowtime) {
		this.borrowtime = borrowtime;
	}
	
	
	@Column(name="flag",length=50)
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name="bookname",length=50)
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	@Column(name="username",length=50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="picture",length=100)
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Column(name="bookcode",length=50)
	public String getBookcode() {
		return bookcode;
	}
	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
	}
	
	
	@Override
	public String toString() {
		return "Record [id=" + id + ", useropenid=" + useropenid + ", username=" + username + ", bookname=" + bookname
				+ ", bookcode=" + bookcode + ", borrowtime=" + borrowtime + ", flag=" + flag + ", picture=" + picture
				+ "]";
	}
	
	

		
}
	
	
	
	
	
	
	

