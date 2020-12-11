package com.alexme951.parseinetstores.repository.parsing;

import com.alexme951.parseinetstores.repository.dto.parsing.CategoryLinkParsingDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryLinkRepository extends CrudRepository<CategoryLinkParsingDto, Long> {
}
