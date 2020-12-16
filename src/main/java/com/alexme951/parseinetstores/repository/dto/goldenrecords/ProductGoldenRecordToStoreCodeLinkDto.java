package com.alexme951.parseinetstores.repository.dto.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_golden_record_to_code_link")
public class ProductGoldenRecordToStoreCodeLinkDto extends BasicDto {
  @OneToOne
  ProductGoldenRecordDto productGoldenRecord;
  @OneToOne
  private CatalogCodeDto catalogCode;

  public ProductGoldenRecordToStoreCodeLinkDto() {
    super();
  }

  public ProductGoldenRecordToStoreCodeLinkDto(ProductGoldenRecordDto productGoldenRecord,
      CatalogCodeDto catalogCode) {
    this.productGoldenRecord = productGoldenRecord;
    this.catalogCode = catalogCode;
  }
}
