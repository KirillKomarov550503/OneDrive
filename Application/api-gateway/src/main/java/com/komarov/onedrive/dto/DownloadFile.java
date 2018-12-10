package com.komarov.onedrive.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class DownloadFile implements Serializable {

  private String fileName;
  private byte[] bytes;
  private String contentType;

  public DownloadFile() {
  }

  public DownloadFile(String fileName, byte[] bytes, String contentType) {
    this.fileName = fileName;
    this.bytes = bytes;
    this.contentType = contentType;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DownloadFile that = (DownloadFile) o;
    return Objects.equals(fileName, that.fileName) &&
        Arrays.equals(bytes, that.bytes) &&
        Objects.equals(contentType, that.contentType);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(fileName, contentType);
    result = 31 * result + Arrays.hashCode(bytes);
    return result;
  }

  @Override
  public String toString() {
    return "DownloadFile{" +
        "fileName='" + fileName + '\'' +
        ", bytes=" + Arrays.toString(bytes) +
        ", contentType='" + contentType + '\'' +
        '}';
  }
}
