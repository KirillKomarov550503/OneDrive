package com.komarov.onedrive.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "file-service")
public interface FileClient {

  @GetMapping(value = "/statistics/files/average")
  ResponseEntity<Double> getAverageSize(
      @RequestParam(name = "personId", required = false) Long personId);

  @GetMapping(value = "/statistics/files/general")
  ResponseEntity<Long> getGeneralSize(
      @RequestParam(name = "personId", required = false) Long personId);
}
