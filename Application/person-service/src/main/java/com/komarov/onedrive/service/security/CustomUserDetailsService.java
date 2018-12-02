package com.komarov.onedrive.service.security;

import com.komarov.onedrive.dao.entity.Person;
import com.komarov.onedrive.dao.repository.PersonRepository;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
  private PersonRepository personRepository;

  @Autowired
  public CustomUserDetailsService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Person person = personRepository.findPersonByEmail(email);
    LOG.info("Load person by email: " + person);
    CustomUser customUser = null;
    if (person != null) {
      customUser = new CustomUser(person.getEmail(),
          person.getPassword(),
          Collections
              .singleton(new SimpleGrantedAuthority(person.getRole().name())),
          person.getId());
    }
    return customUser;
  }
}
