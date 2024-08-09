package br.com.bruno.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bruno.data.vo.v1.PersonVO;
import br.com.bruno.data.vo.v2.PersonVOV2;
import br.com.bruno.exceptions.ResouceNotFoundException;
import br.com.bruno.mapper.DozerMapper;
import br.com.bruno.mapper.custom.PersonMapper;
import br.com.bruno.model.Person;
import br.com.bruno.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {

		logger.info("Encontrando todas as pessoas!");

		return DozerMapper.parcerListObject(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {

		logger.info("Encontrar uma pessoa! ");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Nenhum registro encontrado para este ID !"));
		return DozerMapper.parcerObject(entity, PersonVO.class);
	}

	// Criar
	public PersonVO create(PersonVO person) {

		logger.info("Criando uma pessoa!");
		var entity = DozerMapper.parcerObject(person, Person.class);
		var vo = DozerMapper.parcerObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	
	// Criar no modo V2
	public PersonVOV2 createV2(PersonVOV2 person) {
		
		logger.info("Criando uma pessoa na V2!");
		var entity = mapper.convertVoToEntity(person);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		return vo;
	}

	
	// Atualizar
	public PersonVO update(PersonVO person) {

		logger.info("Atualize uma pessoa!");

		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResouceNotFoundException("Nenhum registro encontrado para este ID !"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parcerObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	// Deletar
	public void delete(Long id) {

		logger.info("Exclua uma pessoa!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Nenhum registro encontrado para este ID !"));

		repository.delete(entity);
	}

}
