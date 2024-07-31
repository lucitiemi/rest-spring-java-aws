package br.com.luciana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luciana.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
