package com.komarov.onedrive.controller;

import com.komarov.onedrive.dto.DownloadFile;
import com.komarov.onedrive.dto.FileEntityDTO;
import com.komarov.onedrive.dto.FileLists;
import com.komarov.onedrive.feign.FileClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FileController extends BaseController {

  private final FileClient fileClient;
  private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

  @Autowired
  public FileController(FileClient fileClient) {
    this.fileClient = fileClient;
  }


  @GetMapping(value = "/users/files/add")
  public ModelAndView showAddPage(ModelAndView modelAndView) {
    modelAndView.setViewName("add");
    modelAndView.addObject("fileList", fileClient.findAddFiles(getCustomUser().getId()).getBody());
    return modelAndView;
  }

  @PostMapping(value = "/users/files/add")
  public ModelAndView addFiles(@RequestParam(name = "files") MultipartFile[] files,
      ModelAndView modelAndView) {
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
    fileClient
        .addFiles(getCustomUser().getId(), new FileLists(fileNames, sizes, bodies, contentTypes));
    modelAndView.addObject("fileList", fileClient.findAddFiles(getCustomUser().getId()).getBody());
    modelAndView.setViewName("add");
    return modelAndView;
  }

  @GetMapping(value = "/users/files/downloadPage")
  public ModelAndView showDownloadPage(ModelAndView modelAndView) {
    modelAndView.setViewName("download");
    modelAndView.addObject("fileList", fileClient.findAddFiles(getCustomUser().getId()).getBody());
    return modelAndView;
  }

  @GetMapping(value = "/users/files/download")
  public ResponseEntity downloadFile(@RequestParam(name = "id") Long fileId) {
    ResponseEntity<DownloadFile> responseEntity = fileClient
        .downloadFile(fileId, getCustomUser().getId());
    DownloadFile file = responseEntity.getBody();
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(file.getContentType()))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFileName()).body(file.getBytes());

  }

  @GetMapping(value = "/users/files")
  public ModelAndView findFile(ModelAndView modelAndView) {
    LOG.info("Person ID: " + getCustomUser().getId());
    List<FileEntityDTO> fileList = fileClient.findAddFiles(getCustomUser().getId()).getBody();
    modelAndView.setViewName("look");
    modelAndView.addObject("fileList", fileList);
    return modelAndView;
  }

  @GetMapping(value = "/users/files/deletePage")
  public ModelAndView showDeleteDelete(ModelAndView modelAndView) {
    modelAndView.setViewName("remove");
    modelAndView.addObject("fileList", fileClient.findAddFiles(getCustomUser().getId()).getBody());
    return modelAndView;
  }

  @GetMapping(value = "/users/files/delete")
  public ModelAndView deleteFile(@RequestParam(name = "id") long fileId,
      ModelAndView modelAndView) {
    LOG.info("Delete file with id: " + fileId);
    fileClient.deleteFileById(fileId, getCustomUser().getId());
    modelAndView.addObject("fileList", fileClient.findAddFiles(getCustomUser().getId()).getBody());
    modelAndView.setViewName("remove");
    return modelAndView;
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
}
