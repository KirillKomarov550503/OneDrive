package com.komarov.onedrive.feign;

import com.komarov.onedrive.dto.FileEntityDTO;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient("file-service")
public interface FileClient {

  @PostMapping("/files")
  ResponseEntity addFiles(@RequestBody MultipartFile[] files,
      @RequestParam(name = "personId") long personId);

  @GetMapping("/files/{fileId}")
  ResponseEntity downloadFile(@PathVariable long fileId);

  @PostMapping("/single_file")
  ResponseEntity addSingleFile(@RequestBody MultipartFile file,
      @RequestParam(name = "personId") long personId);

  @GetMapping("/files")
  ResponseEntity<List<FileEntityDTO>> findAddFiles(
      @RequestParam(name = "personId", required = false) Long personId);

  @DeleteMapping("/files/{fileId}")
  void deleteFileById(@PathVariable long fileId);

}
