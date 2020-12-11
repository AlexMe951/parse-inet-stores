package com.alexme951.parseinetstores.repository.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.goldenrecords.ProductGoldenRecordToStoreCodeLinkDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGoldenRecordToStoreCodeLinkRepository extends
    CrudRepository<ProductGoldenRecordToStoreCodeLinkDto, Long> {

}
