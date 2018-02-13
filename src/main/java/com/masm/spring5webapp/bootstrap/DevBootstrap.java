package com.masm.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.masm.spring5webapp.model.Author;
import com.masm.spring5webapp.model.Book;
import com.masm.spring5webapp.model.Publisher;
import com.masm.spring5webapp.repositories.AuthorRepository;
import com.masm.spring5webapp.repositories.BookRepository;
import com.masm.spring5webapp.repositories.PublisherRepository;

/**
 * Created by jt on 5/16/17.
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
    	
    	Publisher foo = new Publisher();
    	foo.setName("foo");
    	
    	Publisher boo = new Publisher();
    	boo.setName("boo");
    	
    	publisherRepository.save(foo);
    	publisherRepository.save(boo);
    	
        //Eric
        Author eric = new Author("Eric", "Evans");
        Book  ddd = new Book("Domain Driven Design", "1234", foo);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", boo);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        
        Author marco = new Author("Marco", "Machado");
        Book javaPro = new Book("Dev Java Profissionais", "1111", boo);
        marco.getBooks().add(javaPro);
        javaPro.getAuthors().add(marco);
        
        authorRepository.save(marco);
        bookRepository.save(javaPro);
    }
}