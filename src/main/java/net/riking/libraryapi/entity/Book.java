package net.riking.libraryapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_book")
public class Book implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String booktitle;  //书名
	private String author;     //作者
	private Integer stock;      //库存数量
	private String code;       //ISBN码
	private String publish;    //出版社
	private String pubdate;   //出版日期
	private String summary;     //简介
	private String author_intro;//作者简介
	private String picture;    //封面图
	private String catalog;     //目录
	private String numraters;   //评分个数
	private String average;     //平均分

	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="booktitle",length=50)
	public String getBooktitle() {
		return booktitle;
	}	

	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	
	
	@Column(name="author",length=50)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
	@Column(name="code",unique=true,length=50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

	@Column(name="publish",length=50)
	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	
	@Column(name="picture",length=100)
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	@Column(name="pubdate",length=50)
	public String getPubdate() {
		return pubdate;
	}

	

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	@Column(name="summary",length=5000)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name="author_intro",length=5000)
	public String getAuthor_intro() {
		return author_intro;
	}

	public void setAuthor_intro(String author_intro) {
		this.author_intro = author_intro;
	}

	@Column(name="catalog",length=5000)
	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	@Column(name="numraters",length=50)
	public String getNumraters() {
		return numraters;
	}

	public void setNumraters(String numraters) {
		this.numraters = numraters;
	}

	@Column(name="average",length=50)
	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	
	@Column(name="stock",nullable=false)
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", booktitle=" + booktitle + ", author=" + author + ", stock=" + stock + ", code="
				+ code + ", publish=" + publish + ", pubdate=" + pubdate + ", summary=" + summary + ", author_intro="
				+ author_intro + ", picture=" + picture + ", catalog=" + catalog + ", numraters=" + numraters
				+ ", average=" + average + "]";
	}


}



	

	


