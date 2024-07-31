package br.com.luciana.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;		// import feito manualmente
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;	// import feito manualmente
import org.springframework.stereotype.Service;

import br.com.luciana.controllers.PersonController;
import br.com.luciana.exceptions.RequiredObjectIsNullException;
import br.com.luciana.exceptions.ResourceNotFoundException;
import br.com.luciana.mapper.DozerMapper;
import br.com.luciana.mapper.custom.PersonMapper;
import br.com.luciana.model.Person;
import br.com.luciana.repositories.PersonRepository;
import br.com.luciana.vo.v1.PersonVO;
import br.com.luciana.vo.v2.PersonVOv2;

@Service
public class PersonService {
		
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonMapper mapper;
	
	
	// BUSCA TODOS
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");					// imprime no console
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	
	// BUSCA POR ID
	public PersonVO findById(Long id) {
		logger.info("Finding on person!");					// imprime no console
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	
	// CRIA
	public PersonVO create(PersonVO person) {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating a person!");					// imprime no console
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	public PersonVOv2 createV2(PersonVOv2 person) {
		logger.info("Creating a person with V2!");					// imprime no console
		var entity = mapper.convertVoToEntity(person);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		return vo;
	}
	
	
	// ATUALIZA
	public PersonVO update(PersonVO person) {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating a person!");					// imprime no console
		var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	// DELETA
	public void delete(Long id) {
		logger.info("Deleting a person!");					// imprime no console
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}


	
	


}
