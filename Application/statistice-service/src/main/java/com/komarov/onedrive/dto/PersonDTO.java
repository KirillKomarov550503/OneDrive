package com.komarov.onedrive.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonDTO implements Serializable {

  @ApiModelProperty(position = 1, readOnly = true, hidden = true)
  private long id;
  @ApiModelProperty(position = 2)
  private String name;
  @ApiModelProperty(position = 3)
  private String surname;
  @ApiModelProperty(position = 4)
  private String email;

  private Role role;

  @ApiModelProperty(position = 5)
  private String password;

  @ApiModelProperty(position = 6, readOnly = true, hidden = true)
  private Date date;

  public PersonDTO() {
  }

  public PersonDTO(long id, String name, String surname) {
    this.id = id;
    this.name = name;
    this.surname = surname;
  }

  public PersonDTO(long id, String name, String surname, String email, Date date) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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
        Objects.equals(email, personDTO.email) &&
        Objects.equals(password, personDTO.password) &&
        Objects.equals(date, personDTO.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, email, password, date);
  }

  @Override
  public String toString() {
    return "PersonDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", date=" + date +
        '}';
  }
}

