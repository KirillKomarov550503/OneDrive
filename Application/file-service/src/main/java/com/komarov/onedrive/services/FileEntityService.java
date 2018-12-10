package com.komarov.onedrive.services;

import com.komarov.onedrive.dao.entity.FileEntity;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import java.util.List;

public interface FileEntityService {

  FileEntityDTO addFile(FileEntityDTO dto, byte[] body, long personId, String type);

  FileEntity findFileById(long id, long personId);

  List<FileEntityDTO> findFilesByPersonId(long personId);

  void deleteFileById(long id, long personId);

  double findAverageFileSize();

  double findAverageFileSizeByPersonId(long personId);

  long findGeneralFileSizeSum();

  long findGeneralFileSizeSumByPersonId(long personId);

  List<FileEntityDTO> findAllFiles();

}
