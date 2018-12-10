package com.komarov.onedrive.services.impl;

import com.komarov.onedrive.dao.entity.FileEntity;
import com.komarov.onedrive.dao.repository.FileEntityRepository;
import com.komarov.onedrive.services.FileEntityService;
import com.komarov.onedrive.services.dto.converter.FileEntityConverter;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import com.komarov.onedrive.services.exception.ForbiddenException;
import com.komarov.onedrive.services.exception.NotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class FileEntityServiceImpl implements FileEntityService {

  private static final Logger LOG = LoggerFactory.getLogger(FileEntityServiceImpl.class);
  private final FileEntityRepository fileEntityRepository;
  private final FileEntityConverter fileEntityConverter;

  @Autowired
  public FileEntityServiceImpl(FileEntityRepository fileEntityRepository,
      FileEntityConverter fileEntityConverter) {
    this.fileEntityRepository = fileEntityRepository;
    this.fileEntityConverter = fileEntityConverter;
  }

  private List<FileEntityDTO> convertEntityListToDtoList(List<FileEntity> entityList) {
    return entityList.stream().map(fileEntityConverter::convertToDTO).collect(Collectors.toList());
  }

  @Transactional
  @Override
  public FileEntityDTO addFile(FileEntityDTO dto, byte[] body, long personId, String type) {
    FileEntity fileEntity = new FileEntity();
    fileEntity.setFile(body);
    fileEntity.setName(StringUtils.cleanPath(dto.getFileName()));
    fileEntity.setSize(dto.getSize());
    fileEntity.setPersonId(personId);
    fileEntity.setDate(new Date());
    fileEntity.setFileType(type);
    LOG.info(
        "Add file with name \"" + dto.getFileName() + "\" for person with ID: " + personId);
    return fileEntityConverter.convertToDTO(fileEntityRepository.save(fileEntity));
  }

  private File convertByteArrayToByte(FileEntity fileEntity) {
    File file = new File(fileEntity.getName());
    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      fileOutputStream.write(fileEntity.getFile());
    } catch (IOException e) {
      LOG.info("IOException: " + e.getMessage());
    }
    return file;
  }

  @Override
  public FileEntity findFileById(long id, long personId) {
    LOG.info("Find file by id: " + id);
    Optional<FileEntity> optionalFileEntity = fileEntityRepository.findById(id);
    if (!optionalFileEntity.isPresent()) {
      throw new NotFoundException(String.format("File with id %s not found", id));
    }
    if (optionalFileEntity.get().getPersonId() != personId) {
      String error = "Attempt to access someone else's resource";
      LOG.error(error);
      throw new ForbiddenException(error);
    }
    return optionalFileEntity.get();
  }

  @Transactional
  @Override
  public void deleteFileById(long id, long personId) {
    Optional<FileEntity> optionalFile = fileEntityRepository.findById(id);
    if (!optionalFile.isPresent()) {
      String error = String.format("File with id %d not found", id);
      LOG.error(error);
      throw new NotFoundException(error);
    }
    if (optionalFile.get().getPersonId() != personId) {
      String error = "Attempt to access someone else's resource";
      LOG.error(error);
      throw new ForbiddenException(error);
    }
    fileEntityRepository.deleteById(id);
  }

  @Override
  public double calculateAverageSizeOfFiles() {
    List<FileEntity> files = fileEntityRepository.findAll();
    return files.stream().mapToLong(FileEntity::getSize).average().orElse(0);
  }

  @Transactional
  @Override
  public List<FileEntityDTO> findFilesByPersonId(long personId) {
    LOG.info("Find files by person ID: " + personId);
    return convertEntityListToDtoList(fileEntityRepository.findFileEntitiesByPersonId(personId));
  }

  @Override
  public List<FileEntityDTO> findAllFiles() {
    return convertEntityListToDtoList(fileEntityRepository.findAll());
  }
}
