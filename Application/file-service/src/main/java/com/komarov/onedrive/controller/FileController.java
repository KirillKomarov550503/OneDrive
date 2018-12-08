package com.komarov.onedrive.controller;

import com.google.common.net.HttpHeaders;
import com.komarov.onedrive.dao.entity.FileEntity;
import com.komarov.onedrive.services.FileEntityService;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

  private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
  private final FileEntityService fileEntityService;

  @Autowired
  public FileController(FileEntityService fileEntityService) {
    this.fileEntityService = fileEntityService;
  }

  @PostMapping("/files")
  public ResponseEntity addFiles(@RequestBody MultipartFile[] files,
      @RequestParam(name = "personId") long personId) {
    LOG.info("Add files");
    List<FileEntityDTO> dtos = Arrays.stream(files)
        .map(file -> fileEntityService.addFile(file, personId))
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/files/{fileId}")
  public ResponseEntity downloadFile(@PathVariable long fileId) {
    FileEntity fileEntity = fileEntityService.findFileById(fileId);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + fileEntity.getName()).body(fileEntity.getFile());
  }

  @PostMapping("/single_file")
  public ResponseEntity addSingleFile(@RequestBody MultipartFile file,
      @RequestParam(name = "personId") long personId) {
    LOG.info("Add single file");
    return ResponseEntity.ok(fileEntityService.addFile(file, personId));
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileEntityDTO>> findFile(
      @RequestParam(name = "personId", required = false) Long personId) {
    LOG.info("Person ID: " + personId);
    if (personId == null) {
      return ResponseEntity.ok(fileEntityService.findAllFiles());
    } else {
      return ResponseEntity.ok(fileEntityService.findFilesByPersonId(personId));
    }
  }

  @DeleteMapping("/files/{fileId}")
  public void deleteFileById(@PathVariable long fileId) {
    LOG.info("Delete file with ID: " + fileId);
    fileEntityService.deleteFileById(fileId);
  }

}
