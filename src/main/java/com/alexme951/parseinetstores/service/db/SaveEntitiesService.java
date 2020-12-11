package com.alexme951.parseinetstores.service.db;

import com.alexme951.parseinetstores.repository.dto.BasicDto;

public interface SaveEntitiesService {
   <T extends BasicDto> T save(T entity);
}
