package com.example;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.author.Author;
import com.example.author.AuthorRepository;
import com.example.book.Book;
import com.example.book.BookDAO;
import com.example.poema.Poema;
import com.example.poema.PoemaRepository;
import com.example.poema.copy.PoemaCopy;
import com.example.poema.copy.PoemaRepositoryCopy;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	/* implements CommandLineRunner
	 * As our Application implements CommandLineRunner, the run method is invoked automatically when boot starts. 
	 * You should see something like this
	 */
	
	
	//Estans a dincs del database test
	//show databases ---> test
	//show collecions, aquí hi ha el Book, authors, poema  @Document (collection = "authors")
	@Autowired
	private PoemaRepository repository;
	//private PoemaRepositoryCopy repository;
	
	
	// TODO i author repository??
	@Autowired 
	private AuthorRepository repositoryAuthor;
	
	@Autowired
	  BookDAO bookDAO;

	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();
		repositoryAuthor.deleteAll();

		Poema entity = new Poema("poema insertat", "el text del poema", new Date(), "autor", "imatge");
		repository.insert(entity);
		Author entitya = new Author("autorname", "autorsurname", "autornick", "autordescription");
		repositoryAuthor.insert(entitya);
		
		
		// save a couple of customers
		repository.save(new Poema("Títol1", "Text\n1", new Date(), "Autor1", "path img1"));
		repository.save(new Poema/*Copy*/("UNA ROSA VORA MAR", "Darrera les ones amb flaire marina\nresseguim el record d’una vesprada rogenca\n"
				+ "quan atrapem amb coratge les ombres\namb les mans plenes de colors i mirades.\n"
				+ "Tot el que tenim per dir-nos és fa silenci\nquan t’entrego vora mar una rosa.",new Date(),  
				"PeP Panosa – Jardiner de Sentiments", "path img1"));
		repository.save(new Poema/*Copy*/("161226 A LES MEVES FILLES", "M’agradaria deixar-vos\nen cada estel compartit\nun poc de mi,\n\n"
				+ "amb records clars\ncom la brisa que pentina l'Empordà\nun dia primavera.\n\nUn pensament\ndolç i lleu per cada tarda\nfort i pur dins els somnis.",
				new Date(), "PeP Panosa – Jardiner de Sentiments", "path img2"));

		// fetch all customers
		System.out.println("\nCustomers found with findAll():");
		System.out.println("-------------------------------");
		for (Poema/*Copy*/ poema : repository.findAll()) {
			System.out.println(poema);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("\nCustomer found with findByFirstName('PeP Panosa – Jardiner de Sentiments'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByAuthor("PeP Panosa – Jardiner de Sentiments"));

		System.out.println("\nCustomers found with findAll():");
		System.out.println("--------------------------------");
		for (Poema/*Copy*/ poema : repository.findAll()) {
			System.out.println(poema);
		}
		
		
		//************** BOOK *******************
	//	bookDAO.dropCollectionIfExist();
	    Book b = new Book("A Tale Of Two Cities", "Charles Dickens", "Novel", 10);

	    bookDAO.insert(b);

	    Book[] books = new Book[]{
	      new Book("The Da Vinci Code", "Dan Brown", "thriller", 12),
	      new Book("Think and Grow Rich", "Napoleon Hill", "Motivational", 10),
	      new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 8),
	      new Book("Le Petit Prince", "Antoine de Saint-Exupery","Novel", 8)
	    };

	    bookDAO.insertAll(books);

	    Book b1 = bookDAO.findByTitle("The Hobbit");
	    System.out.println("Retrieved Book=" + b1);
	    b1.setPrice(6);

	    bookDAO.update(b1);
	    Book b2 = bookDAO.findByTitle("The Hobbit");
	    System.out.println("Retrieved Book after update=" + b2);

	    int count = bookDAO.deleteByTitle("Think and Grow Rich");
	    System.out.println("Number of records deleted=" + count);
	}
}
