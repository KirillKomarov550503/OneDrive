package com.komarov.onedrive.dto;

import java.util.Objects;

public class StatisticFileDTO {

  private String email;
  private double averageSize;
  private long generalSize;

  public StatisticFileDTO(String email, double averageSize, long generalSize) {
    this.email = email;
    this.averageSize = averageSize;
    this.generalSize = generalSize;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public double getAverageSize() {
    return averageSize;
  }

  public void setAverageSize(double averageSize) {
    this.averageSize = averageSize;
  }

  public long getGeneralSize() {
    return generalSize;
  }

  public void setGeneralSize(long generalSize) {
    this.generalSize = generalSize;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatisticFileDTO that = (StatisticFileDTO) o;
    return Double.compare(that.averageSize, averageSize) == 0 &&
        generalSize == that.generalSize &&
        Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, averageSize, generalSize);
  }

  @Override
  public String toString() {
    return "StatisticFileDTO{" +
        "email='" + email + '\'' +
        ", averageSize=" + averageSize +
        ", generalSize=" + generalSize +
        '}';
  }
}
