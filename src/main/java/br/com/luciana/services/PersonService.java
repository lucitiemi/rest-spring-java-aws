package br.com.luciana.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.luciana.model.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		logger.info("Finding all people!");		// imprime no console
		List<Person> persons = new ArrayList<>();
		for (int i=0; i<8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
			}
		return persons;
	}
	


	public Person findById(String id) {
		logger.info("Finding on person!");		// imprime no console
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Leandro");
		person.setLastName("Costa");
		person.setAdress("Uberlandia - Minas Gerais - Brasil");
		person.setGender("Male");
		return person;
	}
	
	
	public Person create(Person person) {
		logger.info("Creating a person!");		// imprime no console
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Creating a person!");		// imprime no console
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting a person!");		// imprime no console

	}
	
	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAdress("Some address in Brasil");
		person.setGender("Male");
		return person;

	}

}
