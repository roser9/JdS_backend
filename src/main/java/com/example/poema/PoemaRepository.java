package com.example.poema;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PoemaRepository extends MongoRepository <Poema, String>{
	
	 
	 
	public List<Poema> findByTitle(String title);
	public List<Poema> findByAuthor(String author);
//	public List<Poema> findByDate(String date_ini, String date_fi);
//tmc	public Poema insert (Poema p);

}
