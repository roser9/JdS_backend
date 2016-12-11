package com.example.poema;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poema {
	@Id
	private String id;
	private String title;
	private String text;
	private Date date;
	private String author;
	private String img;
	
	
	public Poema(String title, String text, Date date, String author, String img) {
		super();
		this.title = title;
		this.text = text;
		this.date = date;
		this.author = author;
		this.img = img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Poema [id=" + id + ", title=" + title + ", text=" + text + ", date=" + date + ", author=" + author
				+ ", img=" + img + "]";
	}

	
	
	
	
}
