package br.com.luciana.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class) ;
	}

	
	// BUSCA POR ID
	public PersonVO findById(Long id) {
		logger.info("Finding on person!");					// imprime no console
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	
	// CRIA
	public PersonVO create(PersonVO person) {
		logger.info("Creating a person!");					// imprime no console
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
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
		logger.info("Updating a person!");					// imprime no console
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		return vo;
	}
	
	// DELETA
	public void delete(Long id) {
		logger.info("Deleting a person!");					// imprime no console
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}


	
	


}
