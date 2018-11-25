package com.komarov.onedrive.service.dto.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class CredentialDTO implements Serializable {

  private long id;
  private String email;

  private String password;

  public CredentialDTO() {
  }

  public CredentialDTO(long id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CredentialDTO that = (CredentialDTO) o;
    return id == that.id &&
        Objects.equals(email, that.email) &&
        Objects.equals(password, that.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password);
  }

  @Override
  public String toString() {
    return "CredentialDTO{" +
        "id=" + id +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
