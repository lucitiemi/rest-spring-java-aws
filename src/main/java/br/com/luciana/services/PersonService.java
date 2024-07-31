package br.com.luciana.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luciana.exceptions.ResourceNotFoundException;
import br.com.luciana.model.Person;
import br.com.luciana.repositories.PersonRepository;

@Service
public class PersonService {
		
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	
	// BUSCA TODOS
	public List<Person> findAll() {
		logger.info("Finding all people!");		// imprime no console
		return repository.findAll();
	}

	
	// BUSCA POR ID
	public Person findById(Long id) {
		logger.info("Finding on person!");		// imprime no console
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	
	// CRIA
	public Person create(Person person) {
		logger.info("Creating a person!");		// imprime no console
		return repository.save(person);
	}
	
	// ATUALIZA
	public Person update(Person person) {
		logger.info("Updating a person!");		// imprime no console
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	// DELETA
	public void delete(Long id) {
		logger.info("Deleting a person!");		// imprime no console
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	


}
