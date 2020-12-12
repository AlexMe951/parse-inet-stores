package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.dto.ParsingDto;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryParsingDto extends ParsingDto {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @OneToOne(fetch = FetchType.LAZY)
  private CategoryParsingDto parentCategory;

  public CategoryParsingDto() {
    super();
  }

  public CategoryParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      @NotNull String name, @NotNull String description,
      CategoryParsingDto parentCategory) {
    super(id, parsingHistoryEntry);
    this.name = name;
    this.description = description;
    this.parentCategory = parentCategory;
  }
}
