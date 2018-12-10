package com.komarov.onedrive.services;

import com.komarov.onedrive.dao.entity.FileEntity;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import com.komarov.onedrive.services.exception.NotFoundException;
import java.util.List;

public interface FileEntityService {

  FileEntityDTO addFile(FileEntityDTO dto, byte[] body, long personId, String type);

  FileEntity findFileById(long id, long personId);

  List<FileEntityDTO> findFilesByPersonId(long personId);

  void deleteFileById(long id, long personId);

  double calculateAverageSizeOfFiles();

  List<FileEntityDTO> findAllFiles();

}
