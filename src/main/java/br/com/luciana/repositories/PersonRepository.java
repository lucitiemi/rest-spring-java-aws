package br.com.luciana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luciana.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
