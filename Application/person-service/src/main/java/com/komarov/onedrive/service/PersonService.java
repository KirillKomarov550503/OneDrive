package com.komarov.onedrive.service;

import com.komarov.onedrive.dao.entity.Person;
import com.komarov.onedrive.dao.entity.Role;
import com.komarov.onedrive.service.dto.entity.PersonDTO;
import com.komarov.onedrive.service.exception.LogicException;
import com.komarov.onedrive.service.exception.NotFoundException;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface PersonService {
  PersonDTO register(PersonDTO personDTO, Role role) throws LogicException;
  PersonDTO update(PersonDTO personDTO) throws LogicException, NotFoundException;
  PersonDTO find(long id) throws NotFoundException;
  List<PersonDTO> findAll();
  ResponseEntity delete(long id) throws NotFoundException;
}
