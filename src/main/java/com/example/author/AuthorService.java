package com.example.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.author.Author;
import com.example.author.AuthorRepository;

@Component
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;

	public List<Author> getAll() {
		return authorRepository.findAll();
	}

	//findBy busca el nom de l'atribut, aquest cas 'name'
	public List<Author> findByName(String name) {
		return authorRepository.findByName(name);
	}	
}
