package com.komarov.onedrive.service.dto.converter.impl;

import com.komarov.onedrive.dao.entity.Person;
import com.komarov.onedrive.service.dto.converter.Converter;
import com.komarov.onedrive.service.dto.entity.PersonDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements Converter<PersonDTO, Person> {

  @Override
  public Person convertToEntity(PersonDTO dto) {
    Person person = new Person();
    person.setId(dto.getId());
    person.setName(dto.getName());
    person.setSurname(dto.getSurname());
    person.setEmail(dto.getEmail());
    person.setPassword(dto.getPassword());
    return person;
  }

  @Override
  public PersonDTO convertToDTO(Person person) {
    PersonDTO dto = new PersonDTO(person.getId(), person.getName(), person.getSurname(),
        person.getEmail(), person.getDate());
    dto.setRole(person.getRole());
    dto.setPassword(person.getPassword());
    return dto;
  }
}
