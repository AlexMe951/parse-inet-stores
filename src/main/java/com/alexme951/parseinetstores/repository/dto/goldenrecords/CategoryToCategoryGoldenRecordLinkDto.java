package com.alexme951.parseinetstores.repository.dto.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import com.alexme951.parseinetstores.repository.dto.parsing.CategoryParsingDto;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category_to_golden_record_link")
public class CategoryToCategoryGoldenRecordLinkDto extends BasicDto {
  @OneToOne
  private CategoryParsingDto parsedCategory;

  @OneToOne
  private CategoryGoldenRecordDto categoryGoldenRecord;

  public CategoryToCategoryGoldenRecordLinkDto() {
    super();
  }

  public CategoryToCategoryGoldenRecordLinkDto(Long id,
      CategoryParsingDto parsedCategory,
      CategoryGoldenRecordDto categoryGoldenRecord) {
    super(id);
    this.parsedCategory = parsedCategory;
    this.categoryGoldenRecord = categoryGoldenRecord;
  }
}
