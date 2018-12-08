package com.komarov.onedrive.dao.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "files")
@Entity
public class FileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Lob
  @Column(name = "bytes")
  private byte[] file;
  private String name;
  private long size;

  @Column(name = "person_id")
  private long personId;

  private String fileType;
  private Date date;

  public FileEntity() {
  }

  public FileEntity(byte[] file, String name, long size, long personId, String fileType,
      Date date) {
    this.file = file;
    this.name = name;
    this.size = size;
    this.personId = personId;
    this.fileType = fileType;
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "FileEntity{" +
        "id=" + id +
        ", file=" + Arrays.toString(file) +
        ", name='" + name + '\'' +
        ", size=" + size +
        ", personId=" + personId +
        ", fileType='" + fileType + '\'' +
        ", date=" + date +
        '}';
  }
}
