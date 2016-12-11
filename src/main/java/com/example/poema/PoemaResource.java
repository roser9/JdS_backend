package com.example.poema;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.Book;

@RestController
@RequestMapping(path="/api/v1/poema", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PoemaResource implements PoemaService {
	
	@Autowired
	private PoemaService poemaService;
	
  @Autowired
  private MongoOperations mongoOps;
  private static final String POEMA_COLLECTION = "Poema";
  
  public PoemaResource(MongoOperations mongoOps) {
	  this.mongoOps = mongoOps;
  }
  
  public PoemaResource() {  }

  public void dropCollectionIfExist() {  
    if (mongoOps.collectionExists(POEMA_COLLECTION)) {
      mongoOps.dropCollection(POEMA_COLLECTION);
      System.out.println("dropped collection " + POEMA_COLLECTION);
    }
  }

  public void insert(Poema p) {
    this.mongoOps.insert(p, POEMA_COLLECTION);
  }

  public void insertAll(Poema[] poemes) {
    mongoOps.insert(Arrays.asList(poemes), POEMA_COLLECTION);
  }
  
  
  public List<Poema> findByTitle(String title) {
    Query query = new Query(Criteria.where("title").is(title));
    return this.mongoOps.find(query, Poema.class, POEMA_COLLECTION);

  }
  
  public List<Poema> findByAuthor(String author) {
    Query query = new Query(Criteria.where("author").is(author));
    return this.mongoOps.find(query, Poema.class, POEMA_COLLECTION);

  }

	@Override
	public List<Poema> getAll() {
		return this.mongoOps.findAll(Poema.class, POEMA_COLLECTION);
	}
  
  /*
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		HttpStatus status = HttpStatus.OK;
		List<Poema> listPoemes = poemaService.getAll();
		return new ResponseEntity<>(listPoemes, status);
	}*/
	
	
	
}
