package com.komarov.onedrive.service.dto.converter;

import java.io.Serializable;

public interface Converter<DTO extends Serializable, Entity>{
  Entity convertToEntity(DTO dto);
  DTO convertToDTO(Entity entity);
}
