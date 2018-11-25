package com.komarov.onedrive.service.dto.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.komarov.onedrive.dao.entity.Credential;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class PersonDTO implements Serializable {
  private long id;
  private String name;
  private String surname;
  private Credential credential;

  public PersonDTO() {
  }

  public PersonDTO(long id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }

  public PersonDTO(long id, String name, String surname,
      Credential credential) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.credential = credential;
  }

  public Credential getCredential() {
    return credential;
  }

  public void setCredential(Credential credential) {
    this.credential = credential;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonDTO personDTO = (PersonDTO) o;
    return id == personDTO.id &&
        Objects.equals(name, personDTO.name) &&
        Objects.equals(surname, personDTO.surname) &&
        Objects.equals(credential, personDTO.credential);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, credential);
  }

  @Override
  public String toString() {
    return "PersonDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", credential=" + credential +
        '}';
  }
}
