package com.alexme951.parseinetstores.service.db.impl;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import com.alexme951.parseinetstores.service.db.SaveEntitiesService;
import org.springframework.stereotype.Service;

@Service
public class SaveEntitiesServiceImpl implements SaveEntitiesService {

  @Override
  public <T extends BasicDto> T save(T entity) {
    return null;
  }
}
