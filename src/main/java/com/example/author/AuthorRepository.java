package com.example.author;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "author", path = "author")
public interface AuthorRepository extends MongoRepository <Author, String> {

	//https://www.safaribooksonline.com/library/view/mongodb-for-java/9781785280276/ch06s02.html
	
	
	
	//findBy busca el nom de l'atribut, aquest cas 'name'
	public List<Author> findByName(@Param("name") String name);

	/*
	@Query("{ 'type' : ?0 }")
	  public List<Book> findByBookType(String type);
	  @Query("{ 'type' : {$ne : ?0} }")
	  public List<Book> findByBookTypeNot(String type);
	  */
}
