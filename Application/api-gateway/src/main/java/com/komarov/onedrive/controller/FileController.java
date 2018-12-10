package com.komarov.onedrive.controller;

import com.komarov.onedrive.dto.DownloadFile;
import com.komarov.onedrive.dto.FileEntityDTO;
import com.komarov.onedrive.dto.FileLists;
import com.komarov.onedrive.feign.FileClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping(value = "/users/single_file")
  public ResponseEntity<List<FileEntityDTO>> uploadSingleFile(@RequestBody MultipartFile file) {

    long personId = getCustomUser().getId();
    LOG.info("Add file with name {} for person with ID {}", file.getOriginalFilename(), personId);
    return fileClient.addFiles(personId,
        new FileLists(Collections.singletonList(StringUtils.cleanPath(file.getOriginalFilename())),
            Collections.singletonList(file.getSize()), Collections.singletonList(convert(file)),
            Collections.singletonList(file.getContentType())));
  }

  @PostMapping(value = "/users/files")
  public ResponseEntity<List<FileEntityDTO>> addFiles(@RequestBody MultipartFile[] files) {
    Map<FileEntityDTO, Byte[]> fileMap = new HashMap<>();
    List<String> fileNames = new ArrayList<>();
    List<Long> sizes = new ArrayList<>();
    List<Byte[]> bodies = new ArrayList<>();
    List<String> contentTypes = new ArrayList<>();
    for (MultipartFile file : files) {
      fileNames.add(file.getOriginalFilename());
      sizes.add(file.getSize());
      bodies.add(convert(file));
      contentTypes.add(file.getContentType());
    }
    return fileClient
        .addFiles(getCustomUser().getId(), new FileLists(fileNames, sizes, bodies, contentTypes));
  }

  private Byte[] convert(MultipartFile file) {
    Byte[] bytes = null;
    try {
      int length = file.getBytes().length;
      bytes = new Byte[length];
      for (int i = 0; i < length; i++) {
        bytes[i] = file.getBytes()[i];
      }
    } catch (IOException e) {
      LOG.error("IOException");
    }
    return bytes;
  }

  @GetMapping(value = "/users/files/{fileId}")
  public ResponseEntity downloadFile(@PathVariable long fileId) {
    ResponseEntity<DownloadFile> responseEntity = fileClient
        .downloadFile(fileId, getCustomUser().getId());
    DownloadFile file = responseEntity.getBody();
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(file.getContentType()))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFileName()).body(file.getBytes());

  }

  @GetMapping(value = "/users/files")
  public ResponseEntity findFile(/*@RequestParam(name = "personId", required = false) Long personId*/) {
    LOG.info("Person ID: " + getCustomUser().getId());
    return fileClient.findAddFiles(getCustomUser().getId());
  }

  @DeleteMapping(value = "/users/files/[fileId}")
  public void deleteFile(@PathVariable long fileId) {
    LOG.info("Delete file with id: " + fileId);
    fileClient.deleteFileById(fileId, getCustomUser().getId());
  }
}
