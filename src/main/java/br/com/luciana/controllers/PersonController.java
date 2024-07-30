package br.com.luciana.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luciana.model.Person;
import br.com.luciana.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping
	public List<Person> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Person findById(@PathVariable(value= "id") String id) {
		return service.findById(id);
	}
	
	@PostMapping
	public Person create(@RequestBody Person person) {
		return service.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person) {
		return service.update(person);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value= "id") String id) {
		service.delete(id);
	}
	
	
}
