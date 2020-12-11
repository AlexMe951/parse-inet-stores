package com.alexme951.parseinetstores.repository.dto.parsing;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryParsingDto extends BasicParsingDto {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @OneToOne
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
