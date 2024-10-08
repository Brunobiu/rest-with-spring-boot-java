package br.com.bruno.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.bruno.data.vo.v2.PersonVOV2;
import br.com.bruno.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
		
	}
	
	//Convertenco para entidades
	public Person convertVoToEntity(PersonVOV2 person) {
		Person entiry = new Person();
		entiry.setId(person.getId());
		entiry.setAddress(person.getAddress());
		//vo.setBirthDay(new Date());
		entiry.setFirstName(person.getFirstName());
		entiry.setLastName(person.getLastName());
		entiry.setGender(person.getGender());
		return entiry;
		
	}
	
}
