package com.komarov.onedrive.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.komarov.onedrive.TestConfig;
import com.komarov.onedrive.dao.entity.Role;
import com.komarov.onedrive.dao.repository.PersonRepository;
import com.komarov.onedrive.service.PersonService;
import com.komarov.onedrive.service.dto.entity.PersonDTO;
import com.komarov.onedrive.service.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PersonServiceImplTest {

  @Autowired
  private PersonService personService;
  @Mock
  private PersonRepository personRepository;

  @Before
  public void setUp() {
    personService
        .register(new PersonDTO("Kirill", "Komarov", "kirya.komarov.97@list.ru", "superadmin"),
            Role.USER);
    personService
        .register(new PersonDTO("Welcome", "Hello", "wel.hel@gmail.com", "NoPassword"), Role.USER);
    personService
        .register(new PersonDTO("Htoe", "Wetnt", "wel.hela@gmail.com", "NoPassword"), Role.USER);
  }

  @Test
  public void testPositiveDelete() {
    assertEquals(3, personService.findAll().size());
    doNothing().when(personRepository).deleteById(1L);
    personRepository.deleteById(1L);
    personService.delete(1L);
    verify(personRepository).deleteById(1L);
    assertEquals(2, personService.findAll().size());
  }

  @Test(expected = NotFoundException.class)
  public void testNegativeDelete() {
    assertEquals(3, personService.findAll().size());
    doThrow(NotFoundException.class).when(personRepository).deleteById(5L);
    personRepository.deleteById(5L);
    verify(personRepository).deleteById(5L);
    personService.delete(5L);
  }
}