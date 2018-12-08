package com.komarov.onedrive.controller;

import com.komarov.onedrive.feign.FileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController extends BaseController {

  private final FileClient fileClient;
  private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

  @Autowired
  public FileController(FileClient fileClient) {
    this.fileClient = fileClient;
  }

  @PostMapping("/single_file")
  public ResponseEntity addSingleFile(@RequestBody MultipartFile file) {
    long personId = getCustomUser().getId();
    LOG.info("Add file with name {} for person with ID {}", file.getOriginalFilename(), personId);
    return fileClient.addSingleFile(file, personId);
  }

}
