package com.komarov.onedrive.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.komarov.onedrive.services.dto.entity.FileEntityDTO;
import java.io.IOException;
import java.util.Map;

public class CustomDeserializer extends KeyDeserializer {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  @Override
  public Object deserializeKey(String s, DeserializationContext deserializationContext)
      throws IOException {
    return MAPPER.readValue(s, new TypeReference<Map<FileEntityDTO, Byte[]>>(){});
  }
}
