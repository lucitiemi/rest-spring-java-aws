package br.com.luciana.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;		// import feito manualmente
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;	// import feito manualmente

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luciana.controllers.BookController;
import br.com.luciana.exceptions.RequiredObjectIsNullException;
import br.com.luciana.exceptions.ResourceNotFoundException;
import br.com.luciana.mapper.DozerMapper;
import br.com.luciana.model.Book;
import br.com.luciana.repositories.BookRepository;
import br.com.luciana.vo.v1.BookVO;


@Service
public class BookService {
		
	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	@Autowired
	private BookRepository repository;
	
	
	// BUSCA TODOS
	public List<BookVO> findAll() {
		logger.info("Finding all books!");					// imprime no console
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	
	// BUSCA POR ID
	public BookVO findById(Long id) {
		logger.info("Finding on book!");					// imprime no console
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	
	// CRIA
	public BookVO create(BookVO book) {
		
		if (book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating a book!");					// imprime no console
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity),BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	
	
	// ATUALIZA
	public BookVO update(BookVO book) {
		
		if (book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating a book!");					// imprime no console
		var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity),BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	// DELETA
	public void delete(Long id) {
		logger.info("Deleting a book!");					// imprime no console
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}


	
	


}
