package com.example.author;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document (collection = "authors")
@JsonIgnoreProperties (ignoreUnknown = true)
public class Author {
	@Id
	private String id;
	private String name;
	private String surname;
	private String nick;
	private String description;
		
	public Author(String name, String surname, String nick, String description) {
		super();
		this.name = name;
		this.surname = surname;
		this.nick = nick;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", surname=" + surname + ", nick=" + nick + ", description="
				+ description + "]";
	}
	
	

}
