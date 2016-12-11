package com.example.book;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOImpl  implements BookDAO {

  @Autowired
  private MongoOperations mongoOps;
  
  private static final String BOOK_COLLECTION = "Book";

  public BookDAOImpl(MongoOperations mongoOps) {
    this.mongoOps = mongoOps;
  }

  public BookDAOImpl() {    }

  public void dropCollectionIfExist() {
  
    if (mongoOps.collectionExists(BOOK_COLLECTION)) {
      mongoOps.dropCollection(BOOK_COLLECTION);
      System.out.println("dropped collection" + BOOK_COLLECTION);
    }
  }

 
  public void insert(Book p) {
    this.mongoOps.insert(p, BOOK_COLLECTION);
  }

  public void insertAll(Book[] books) {
    mongoOps.insert(Arrays.asList(books), BOOK_COLLECTION);
  }

  
  public Book findByTitle(String title) {
    Query query = new Query(Criteria.where("title").is(title));
    return this.mongoOps.findOne(query, Book.class, BOOK_COLLECTION);

  }


  public void update(Book p) {
    this.mongoOps.save(p, BOOK_COLLECTION);
  }

  
  public int deleteByTitle(String title) {
    Query query = new Query(Criteria.where("title").is(title));
    WriteResult result = this.mongoOps.remove(query, Book.class, BOOK_COLLECTION);
    return result.getN();
  }
  
  /*
   	Query query = new Query();
	query.addCriteria(Criteria.where("type").is("Fantasy").and("price").lt(10));
	Book book = mongoOps.findOne(query, Book.class);
	
	Query query = new Query();

	query.addCriteria(
  		Criteria.where("type").is("Fantasy").andOperator(
    		Criteria.where("price").gt(5),
    		Criteria.where("price").lt(10)
  )
);	


	BasicQuery query1 = new BasicQuery("{ price : { $lt : 10 }, type : 'Fantasy' }");
	Book book = mongoOps.findOne(query1, Book.class);
	
   */

}