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
@Table(name = "product_golden_record")
public class ProductGoldenRecordDto extends BasicDto {

  @OneToOne
  CategoryGoldenRecordDto parentCategory;
  @OneToOne
  BrandDto brand;
  private String name;
  private String description;

  public ProductGoldenRecordDto() {
    super();
  }

  public ProductGoldenRecordDto(String name,
      String description,
      CategoryGoldenRecordDto parentCategory,
      BrandDto brand) {
    this.name = name;
    this.description = description;
    this.parentCategory = parentCategory;
    this.brand = brand;
  }
}