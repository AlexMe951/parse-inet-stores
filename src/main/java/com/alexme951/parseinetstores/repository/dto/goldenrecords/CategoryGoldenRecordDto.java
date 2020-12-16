package com.alexme951.parseinetstores.repository.dto.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category_golden_record")
public class CategoryGoldenRecordDto extends BasicDto {
  private String name;

  @OneToOne(fetch = FetchType.LAZY)
  CategoryGoldenRecordDto parentCategory;

  public CategoryGoldenRecordDto() {
    super();
  }

  public CategoryGoldenRecordDto(String name, CategoryGoldenRecordDto parentCategory) {
    this.name = name;
    this.parentCategory = parentCategory;
  }
}
