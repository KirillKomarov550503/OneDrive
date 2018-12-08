package com.komarov.onedrive.services.dto.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(Include.NON_NULL)
public class FileEntityDTO implements Serializable {

  @ApiModelProperty(position = 1, readOnly = true, hidden = true)
  private long id;
  @ApiModelProperty(position = 2, readOnly = true, hidden = true)
  private String fileName;
  @ApiModelProperty(position = 3, readOnly = true, hidden = true)
  private long size;
  @ApiModelProperty(position = 4, readOnly = true, hidden = true)
  private Date date;


  public FileEntityDTO() {
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
}
