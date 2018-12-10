package com.komarov.onedrive.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FileLists implements Serializable {

  private List<String> fileNames;
  private List<Long> sizes;
  private List<Byte[]> bodies;
  private List<String> contentTypes;

  public FileLists(List<String> fileNames, List<Long> sizes, List<Byte[]> bodies,
      List<String> contentTypes) {
    this.fileNames = fileNames;
    this.sizes = sizes;
    this.bodies = bodies;
    this.contentTypes = contentTypes;
  }

  public List<String> getFileNames() {
    return fileNames;
  }

  public void setFileNames(List<String> fileNames) {
    this.fileNames = fileNames;
  }

  public List<Long> getSizes() {
    return sizes;
  }

  public void setSizes(List<Long> sizes) {
    this.sizes = sizes;
  }

  public List<Byte[]> getBodies() {
    return bodies;
  }

  public void setBodies(List<Byte[]> bodies) {
    this.bodies = bodies;
  }

  public List<String> getContentTypes() {
    return contentTypes;
  }

  public void setContentTypes(List<String> contentTypes) {
    this.contentTypes = contentTypes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileLists fileLists = (FileLists) o;
    return Objects.equals(fileNames, fileLists.fileNames) &&
        Objects.equals(sizes, fileLists.sizes) &&
        Objects.equals(bodies, fileLists.bodies) &&
        Objects.equals(contentTypes, fileLists.contentTypes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileNames, sizes, bodies, contentTypes);
  }

  @Override
  public String toString() {
    return "FileLists{" +
        "fileNames=" + fileNames +
        ", sizes=" + sizes +
        ", bodies=" + bodies +
        ", contentTypes=" + contentTypes +
        '}';
  }
}
