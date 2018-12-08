package com.komarov.onedrive.services;

import com.komarov.onedrive.dao.entity.FileEntity;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import com.komarov.onedrive.services.exception.NotFoundException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileEntityService {

  FileEntityDTO addFile(MultipartFile multipartFile, long personId) throws NotFoundException;

  FileEntity findFileById(long id) throws NotFoundException;

  List<FileEntityDTO> findFilesByPersonId(long personId) throws NotFoundException;

  void deleteFileById(long id) throws NotFoundException;

  double calculateAverageSizeOfFiles();

  List<FileEntityDTO> findAllFiles();

}
