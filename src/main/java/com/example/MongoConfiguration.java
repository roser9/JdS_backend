package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
/* The @EnableMongoRepositories annotation is used to activate the MongoDB repositories—by specifying the mapping base package; 
 * it will trigger scanning of the package of annotated classes.*/

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MongoConfiguration extends AbstractMongoConfiguration{
	@Override
    protected String getDatabaseName() {
      return "springdata";
    }

    @Override
    public Mongo mongo() throws Exception {
      return new MongoClient("127.0.0.1", 27017);
    }
  
    @Override
    protected String getMappingBasePackage() {
    	//to return the package (com.mongo.model) in which the model class is defined.
    	return "com.example.book";
    	//return "com.packtpub.mongo.chapter6.repository";
      //com.example.author
    }
}
