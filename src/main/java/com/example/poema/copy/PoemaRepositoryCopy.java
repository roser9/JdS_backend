package com.example.poema.copy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;

public interface PoemaRepositoryCopy extends MongoRepository <PoemaCopy, String>{
	
	 
	 
	public List<PoemaCopy> findByAuthor(String author);
	public List<PoemaCopy> findByDate(String date_ini, String date_fi);

}
