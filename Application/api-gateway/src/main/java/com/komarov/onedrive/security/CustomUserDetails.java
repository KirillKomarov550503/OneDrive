package com.komarov.onedrive.security;

import com.komarov.onedrive.dto.PersonDTO;
import com.komarov.onedrive.feign.PersonClient;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetails implements UserDetailsService {

  private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetails.class);
  private final PersonClient personClient;

  @Autowired
  public CustomUserDetails(PersonClient personClient) {
    this.personClient = personClient;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    ResponseEntity<PersonDTO> responseEntity = personClient.findPersonByEmail(email);
    PersonDTO person = responseEntity.getBody();
    LOG.info("Load person by email: " + person);
    CustomUser customUser = null;
    if (person != null) {
      customUser = new CustomUser(person.getEmail(),
          person.getPassword(),
          Collections.singleton(new SimpleGrantedAuthority(person.getRole().name())),
          person.getId(), person.getRole());
    }
    return customUser;
  }
}
