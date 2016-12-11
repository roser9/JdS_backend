package com.example.poema.copy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/poema", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PoemaResourceCopy {
	@Autowired
	private PoemaServiceCopy poemaService;
	
  @Autowired
  private MongoOperations mongoOps;
  private static final String BOOK_COLLECTION = "Poema";
  
  public PoemaResourceCopy(MongoOperations mongoOps) {
	    this.mongoOps = mongoOps;
	  }
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		HttpStatus status = HttpStatus.OK;
		List<PoemaCopy> listPoemes = poemaService.getAll();
		return new ResponseEntity<>(listPoemes, status);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/findByAuthor/{name}")
	public ResponseEntity<?> findByAuthor(@PathVariable String name) { //agafa el name
		HttpStatus status = HttpStatus.OK;
		List<PoemaCopy> listPoemes = poemaService.findByAuthor(name);
		return new ResponseEntity<>(listPoemes, status);
	}
	

	@RequestMapping(method = RequestMethod.GET, path = "/findByDate/{date_ini}-{date_fi}")
	public ResponseEntity<?> findByDate(@PathVariable String date_ini, @PathVariable String date_fi) {
		HttpStatus status = HttpStatus.OK;
		List<PoemaCopy> listPoemes = poemaService.findByDate(date_ini, date_fi);
		return new ResponseEntity<>(listPoemes, status);
	}

	
	
}
