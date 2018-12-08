package com.komarov.onedrive.services.dto.converter;

import com.komarov.onedrive.dao.entity.FileEntity;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import org.springframework.stereotype.Component;

@Component
public class FileEntityConverter {

  public FileEntityDTO convertToDTO(FileEntity fileEntity) {
    return new FileEntityDTO(fileEntity.getId(), fileEntity.getName(), fileEntity.getSize(),
        fileEntity.getDate());
  }

}
