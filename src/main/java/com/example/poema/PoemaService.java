package com.example.poema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.book.Book;

@Component
public interface PoemaService {

	  public void dropCollectionIfExist();
	  public void insert(Poema p);
	  public void insertAll(Poema[] p);
	  
	public List<Poema> getAll();
	public List<Poema> findByTitle(String title);
	public List<Poema> findByAuthor(String author);
	
	/*@Autowired
	private PoemaRepository poemaRepository;

	public List<Poema> getAll() {
		return poemaRepository.findAll();
	}

	public List<Poema> findByAuthor(String name) {
		return poemaRepository.findByAuthor(name);
	}	
	
	public List<Poema> findByDate(String date_ini, String date_fi) {
		return poemaRepository.findByDate(date_ini, date_fi);
	}*/
}
