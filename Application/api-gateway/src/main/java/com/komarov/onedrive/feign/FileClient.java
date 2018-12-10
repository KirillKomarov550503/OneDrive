package com.komarov.onedrive.feign;

import com.komarov.onedrive.dto.DownloadFile;
import com.komarov.onedrive.dto.FileEntityDTO;
import com.komarov.onedrive.dto.FileLists;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "file-service")
public interface FileClient {

  @PostMapping("/files")
  ResponseEntity<List<FileEntityDTO>> addFiles(
      @RequestParam(name = "personId") long personId,
      @RequestBody FileLists fileLists);

  @GetMapping(value = "/files/{fileId}")
  ResponseEntity<DownloadFile> downloadFile(@PathVariable long fileId,
      @RequestParam(name = "personId") long personId);

  @GetMapping(value = "/files")
  ResponseEntity<List<FileEntityDTO>> findAddFiles(
      @RequestParam(name = "personId", required = false) Long personId);

  @DeleteMapping(value = "/files/{fileId}")
  void deleteFileById(@PathVariable long fileId, @RequestParam(name = "personId") long personId);

}
