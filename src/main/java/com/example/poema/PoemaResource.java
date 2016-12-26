package com.example.poema;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

  
  /*
@RequestMapping(value = "addExpense1", method = RequestMethod.POST)
@ResponseBody
public List<ExpenseDetail> addExpense1(@RequestBody ExpenseDetailDTO expenseDetailDTO) {
    LOG.info("Request to add description : " + expenseDetailDTO.getDescription());
    if(expenseDetailDTO==null || expenseDetailDTO.getDescription()==null || expenseDetailDTO.getAmount()==null)
        return null;
    expenseService.addExpense(expenseDetailDTO);

    List<ExpenseDetail> expenseDetails = expenseService.getAllForToday();
    LOG.info("Expense Details fetched : " + expenseDetails.size());
    return expenseDetails;
}
   */
 // @RequestMapping(value = "/insert", method = RequestMethod.POST, path = "/insert/{poema}") // headers={"name=pankaj", "id=1"})
  /* @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        return "result";
    }*/
  @RequestMapping(value = "insert", method = RequestMethod.POST)
  @ResponseBody
  public void insert(@RequestBody Poema p) {
	System.out.println("***** Request to add POEMA : " + p.getTitle() + "_"  + p.getId());
    try{
    	this.mongoOps.insert(p, POEMA_COLLECTION);
    	System.out.println("***** Request to add POEMA 2 : " + p.getTitle() + "_"  + p.getId());
    	this.mongoOps.save(p);
    	System.out.println("***** Request to add POEMA 3 : " + p.getTitle() + "_"  + p.getId());
    }
    catch(com.mongodb.DuplicateKeyException duplicate){
    	System.out.println("***** duplicate - Request to add POEMA : " + p.getTitle() + "_"  + p.getId());
    	this.mongoOps.save(p);
    }
  }

  @RequestMapping(value = "remove", method = RequestMethod.DELETE)
  @ResponseBody
  public void remove(@RequestBody Poema p){
	System.out.println("***** remove POEMA : " + p.getTitle() + "_"  + p.getId());
	this.mongoOps.remove(p, POEMA_COLLECTION);
  }
  

  @RequestMapping(value = "update", method = RequestMethod.POST)
  @ResponseBody
	public void update(@RequestBody Poema p) {		
		System.out.println("***** Request to UPdate POEMA : " + p.getTitle() + "_"  + p.getId());
	    this.mongoOps.insert(p, POEMA_COLLECTION);
		this.mongoOps.save(p, POEMA_COLLECTION);
	}
 /* @RequestMapping(value = "modify", method = RequestMethod.POST)
  @ResponseBody
  public Poema modify(@RequestBody String id) {
	System.out.println("***** Request to modify POEMA id: " + id);
	  Query query = new Query(Criteria.where("_id").is(id));
	  Update update = new  Update();
	  update.
    this.mongoOps.updateFirst(query, update, POEMA_COLLECTION);
    this.mongoOps.save(p);//TODO cal?
    return p;
  }*/
  
 


  /* public void insert(Poema p) {
    this.mongoOps.insert(p, POEMA_COLLECTION);
  }*/
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
