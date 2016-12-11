package com.example.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api/v1/author", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthorResource {

	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		HttpStatus status = HttpStatus.OK;
		List<Author> listAuthors = authorService.getAll();
		return new ResponseEntity<>(listAuthors, status);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/findByName/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name) { //agafa el name
		HttpStatus status = HttpStatus.OK;
		List<Author> listAuthors = authorService.findByName(name);
		return new ResponseEntity<>(listAuthors, status);
	}
}
