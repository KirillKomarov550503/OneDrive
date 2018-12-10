package com.komarov.onedrive.services.dto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FileEntityDTO implements Serializable {
  @JsonProperty(value = "id")
  @ApiModelProperty(position = 1, readOnly = true, hidden = true)
  private long id;

  @JsonProperty(value = "fileName")
  @ApiModelProperty(position = 2, readOnly = true, hidden = true)
  private String fileName;

  @JsonProperty(value = "size")
  @ApiModelProperty(position = 3, readOnly = true, hidden = true)
  private long size;

  @JsonProperty(value = "date")
  @ApiModelProperty(position = 4, readOnly = true, hidden = true)
  private Date date;


  public FileEntityDTO() {
  }

  public FileEntityDTO(String fileName, long size, Date date) {
    this.fileName = fileName;
    this.size = size;
    this.date = date;
  }

  public FileEntityDTO(long id, String fileName, long size, Date date) {
    this.id = id;
    this.fileName = fileName;
    this.size = size;
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
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
    FileEntityDTO entityDTO = (FileEntityDTO) o;
    return id == entityDTO.id &&
        size == entityDTO.size &&
        Objects.equals(fileName, entityDTO.fileName) &&
        Objects.equals(date, entityDTO.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fileName, size, date);
  }

  @Override
  public String toString() {
    return "FileEntityDTO{" +
        "id=" + id +
        ", fileName='" + fileName + '\'' +
        ", size=" + size +
        ", date=" + date +
        '}';
  }
}
