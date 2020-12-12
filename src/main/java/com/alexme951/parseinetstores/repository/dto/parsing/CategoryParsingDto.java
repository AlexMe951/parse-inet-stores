package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
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
public class CategoryParsingDto extends BasicDto {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @OneToOne(fetch = FetchType.LAZY)
  private CategoryParsingDto parentCategory;

  @OneToOne
  @NotNull
  private ParsingHistoryEntryDto parsingHistoryEntry;

  public CategoryParsingDto() {
    super();
  }

  public CategoryParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      @NotNull String name, @NotNull String description,
      CategoryParsingDto parentCategory) {
    super(id);
    this.parsingHistoryEntry = parsingHistoryEntry;
    this.name = name;
    this.description = description;
    this.parentCategory = parentCategory;
  }

  public void setParsingHistoryEntry(ParsingHistoryEntryDto parsingHistoryEntry) {
    this.parsingHistoryEntry = parsingHistoryEntry;
  }

  public ParsingHistoryEntryDto getParsingHistoryEntry() {
    return this.parsingHistoryEntry;
  }
}
