package com.alexme951.parseinetstores.repository.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.goldenrecords.ProductToGoldenRecordLinkDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductToGoldenRecordLinkRepository  extends
    CrudRepository<ProductToGoldenRecordLinkDto, Long> {

}
