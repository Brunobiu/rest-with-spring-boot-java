package br.com.bruno.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bruno.exceptions.ResouceNotFoundException;
import br.com.bruno.model.Person;
import br.com.bruno.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;

	public List<Person> findAll() {

		logger.info("Encontrando todas as pessoas!");

		return repository.findAll();
	}

	public Person findById(Long id) {

		logger.info("Encontrar uma pessoa! ");

		return repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Nenhum registro encontrado para este ID !"));
	}

	// Criar
	public Person create(Person person) {

		logger.info("Criando uma pessoa!");

		return repository.save(person);
	}

	// Atualizar
	public Person update(Person person) {

		logger.info("Atualize uma pessoa!");

		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResouceNotFoundException("Nenhum registro encontrado para este ID !"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		return repository.save(person);
	}

	// Deletar
	public void delete(Long id) {

		logger.info("Exclua uma pessoa!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Nenhum registro encontrado para este ID !"));

		repository.delete(entity);
	}

}
