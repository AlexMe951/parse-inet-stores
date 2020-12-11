package com.alexme951.parseinetstores.repository.parsing;

import com.alexme951.parseinetstores.repository.dto.parsing.ProductLinkParsingDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLinkRepository  extends CrudRepository<ProductLinkParsingDto, Long> {

}
