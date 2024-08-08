package br.com.bruno.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bruno.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
