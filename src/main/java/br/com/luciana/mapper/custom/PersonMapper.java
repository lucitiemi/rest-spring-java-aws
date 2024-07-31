package br.com.luciana.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.luciana.model.Person;
import br.com.luciana.vo.v2.PersonVOv2;

@Service
public class PersonMapper {
	
	public PersonVOv2 convertEntityToVo (Person person) {
		PersonVOv2 vo = new PersonVOv2();
		vo.setId(person.getId());
		vo.setAdress(person.getAdress());
		vo.setBirthDay(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName()); 
		vo.setGender(person.getGender());
		return vo;
	}
	
	public Person convertVoToEntity (PersonVOv2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setAdress(person.getAdress());
		//entity.setBirthDay(new Date());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName()); 
		entity.setGender(person.getGender());
		return entity;
	}

}
