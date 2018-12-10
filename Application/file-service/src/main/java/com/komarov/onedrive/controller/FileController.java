package com.komarov.onedrive.controller;

import com.komarov.onedrive.dao.entity.FileEntity;
import com.komarov.onedrive.services.FileEntityService;
import com.komarov.onedrive.services.dto.entity.DownloadFile;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import com.komarov.onedrive.services.dto.entity.FileLists;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
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

@RestController
public class FileController {

  private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
  private final FileEntityService fileEntityService;

  @Autowired
  public FileController(FileEntityService fileEntityService) {
    this.fileEntityService = fileEntityService;
  }

  @PostMapping("/files")
  public ResponseEntity<List<FileEntityDTO>> addFiles(
      @RequestParam(name = "personId") long personId,
      @RequestBody FileLists fileLists) {
    LOG.info("Add files");
    List<FileEntityDTO> dtos = new ArrayList<>();
    for (int i = 0; i < fileLists.getFileNames().size(); i++) {
      dtos.add(
          fileEntityService.addFile(
              new FileEntityDTO(fileLists.getFileNames().get(i), fileLists.getSizes().get(i),
                  new Date()),
              ArrayUtils.toPrimitive(fileLists.getBodies().get(i)), personId,
              fileLists.getContentTypes().get(0)));
    }
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/files/{fileId}")
  public ResponseEntity<DownloadFile> downloadFile(@PathVariable long fileId,
      @RequestParam(name = "personId") long personId) {
    FileEntity fileEntity = fileEntityService.findFileById(fileId, personId);
    return ResponseEntity.ok().body(
        new DownloadFile(fileEntity.getName(), fileEntity.getFile(), fileEntity.getFileType()));
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
  public void deleteFileById(@PathVariable long fileId,
      @RequestParam(name = "personId") long personId) {
    LOG.info("Delete file with ID: " + fileId);
    fileEntityService.deleteFileById(fileId, personId);
  }

  @GetMapping("/statistics/files/average")
  public ResponseEntity<Double> getAverageSize(@RequestParam(name = "personId", required = false) Long personId) {
    LOG.info("Get average Size");
    if (personId == null) {
      return ResponseEntity.ok().body(fileEntityService.findAverageFileSize());
    } else {
      return ResponseEntity.ok().body(fileEntityService.findAverageFileSizeByPersonId(personId));
    }
  }

  @GetMapping("/statistics/files/general")
  public ResponseEntity<Long> getGeneralSize(@RequestParam(name = "personId", required = false) Long personId) {
    LOG.info("Get general size");
    if (personId == null) {
      return ResponseEntity.ok().body(fileEntityService.findGeneralFileSizeSum());
    } else {
      return ResponseEntity.ok().body(fileEntityService.findGeneralFileSizeSumByPersonId(personId));
    }
  }
}
