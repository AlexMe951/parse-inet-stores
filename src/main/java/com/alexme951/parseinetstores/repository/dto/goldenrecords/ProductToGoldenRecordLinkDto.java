package com.alexme951.parseinetstores.repository.dto.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import com.alexme951.parseinetstores.repository.dto.parsing.ProductParsingDto;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_to_golden_record_link")
public class ProductToGoldenRecordLinkDto extends BasicDto {

  @OneToOne
  private ProductParsingDto parsedProduct;

  @OneToOne
  private ProductGoldenRecordDto goldenRecord;

  public ProductToGoldenRecordLinkDto() {
    super();
  }

  public ProductToGoldenRecordLinkDto(ProductParsingDto parsedProduct,
      ProductGoldenRecordDto goldenRecord) {
    this.parsedProduct = parsedProduct;
    this.goldenRecord = goldenRecord;
  }
}
