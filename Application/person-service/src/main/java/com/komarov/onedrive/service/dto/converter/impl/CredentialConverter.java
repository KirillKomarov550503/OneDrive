package com.komarov.onedrive.service.dto.converter.impl;

import com.komarov.onedrive.dao.entity.Credential;
import com.komarov.onedrive.service.dto.converter.Converter;
import com.komarov.onedrive.service.dto.entity.CredentialDTO;

public class CredentialConverter implements Converter<CredentialDTO, Credential> {

  @Override
  public Credential convertToEntity(CredentialDTO dto) {
    return new Credential(dto.getEmail(), dto.getPassword());
  }

  @Override
  public CredentialDTO convertToDTO(Credential credential) {
    return new CredentialDTO(credential.getId(), credential.getEmail(), credential.getPassword());
  }
}
