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
    person.setCredential(dto.getCredential());
    return person;
  }

  @Override
  public PersonDTO convertToDTO(Person person) {
    return new PersonDTO(person.getId(), person.getName(), person.getSurname(), person.getCredential());
  }
}
