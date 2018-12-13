package com.komarov.onedrive.service.impl;

import com.komarov.onedrive.dao.entity.Person;
import com.komarov.onedrive.dao.entity.Role;
import com.komarov.onedrive.dao.repository.PersonRepository;
import com.komarov.onedrive.service.PersonService;
import com.komarov.onedrive.service.dto.converter.impl.PersonConverter;
import com.komarov.onedrive.service.dto.entity.PersonDTO;
import com.komarov.onedrive.service.exception.LogicException;
import com.komarov.onedrive.service.exception.NotFoundException;
import com.komarov.onedrive.service.utils.Validation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);
  private final PersonRepository personRepository;
  private final PersonConverter personConverter;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository,
      PersonConverter personConverter, BCryptPasswordEncoder passwordEncoder) {
    this.personRepository = personRepository;
    this.personConverter = personConverter;
    this.passwordEncoder = passwordEncoder;
  }

  private List<PersonDTO> convertPersonListToDtoList(List<Person> persons) {
    return persons.stream()
        .map(personConverter::convertToDTO)
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public PersonDTO register(PersonDTO personDTO, Role role) throws LogicException {
    Validation.validateNameAndSurname(personDTO.getName(), personDTO.getSurname());
    Validation.validateEmail(personDTO.getEmail());
    Validation.validatePassword(personDTO.getPassword());
    Person person = personConverter.convertToEntity(personDTO);
    if (personRepository.findPersonByEmail(personDTO.getEmail()) != null) {
      String error = "User with this email already exist";
      LOG.info(error);
      throw new LogicException(error);
    }
    String password = passwordEncoder.encode(person.getPassword());
    person.setPassword(password);
    person.setRole(role);
    person.setDate(new Date());
    LOG.info("Add new person: " + personDTO);
    return personConverter.convertToDTO(personRepository.save(person));
  }

  @Transactional
  @Override
  public PersonDTO update(PersonDTO personDTO) throws LogicException, NotFoundException {
    Validation.validateNameAndSurname(personDTO.getName(), personDTO.getSurname());
    Validation.validateEmail(personDTO.getEmail());
    Validation.validatePassword(personDTO.getPassword());
    Optional<Person> dbPerson = personRepository.findById(personDTO.getId());
    if (!dbPerson.isPresent()) {
      String error = "Not found person with ID: " + personDTO.getId();
      LOG.info(error);
      throw new LogicException(error);
    }
    Person person = dbPerson.get();
    person.setName(personDTO.getName());
    person.setSurname(personDTO.getSurname());
    String password = passwordEncoder.encode(person.getPassword());
    person.setPassword(password);
    LOG.info("Update person: " + personDTO);
    return personConverter.convertToDTO(personRepository.save(person));
  }

  @Override
  public PersonDTO find(long id) throws NotFoundException {
    Optional<Person> dbPerson = personRepository.findById(id);
    if (!dbPerson.isPresent()) {
      String error = "Not found person with ID: " + id;
      LOG.info(error);
      throw new LogicException(error);
    }
    LOG.info("Select person by id: " + id);
    return personConverter.convertToDTO(dbPerson.get());
  }

  @Override
  public List<PersonDTO> findAll() {
    return convertPersonListToDtoList(personRepository.findPeopleByRole(Role.USER));
  }

  @Transactional
  @Override
  public ResponseEntity delete(long id) throws NotFoundException {
    Optional<Person> dbPerson = personRepository.findById(id);
    if (!dbPerson.isPresent()) {
      String error = "Not exist person with ID: " + id;
      LOG.info(error);
      throw new LogicException(error);
    }
    LOG.info("Delete person by id: " + id);
    personRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public List<PersonDTO> findPeopleByDateBetweenEarlyAndLater(Date early, Date later) {
    return convertPersonListToDtoList(
        personRepository.findPeopleByDateBetweenEarlyAndLater(early, later));
  }

  @Override
  public PersonDTO findByEmail(String email) {
    Person person = personRepository.findPersonByEmail(email);
    if(person == null){
      String message = String.format("Person with email %s not found", email);
      LOG.error(message);
      throw new NotFoundException(message);
    }
    PersonDTO dto = personConverter.convertToDTO(person);
    dto.setPassword(person.getPassword());
    return dto;
  }
}
