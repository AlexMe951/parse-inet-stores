package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductParsingDto extends BasicDto {

  private String name;
  private String description;
  private String code;
  private String attributes;

  @OneToOne
  @NotNull
  private ParsingHistoryEntryDto parsingHistoryEntry;

  public ProductParsingDto() {
    super();
  }

  public ProductParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      String name, String description, String code, String attributes) {
    super(id);
    this.parsingHistoryEntry = parsingHistoryEntry;
    this.name = name;
    this.description = description;
    this.code = code;
    this.attributes = attributes;
  }

  public void setParsingHistoryEntry(ParsingHistoryEntryDto parsingHistoryEntry) {
    this.parsingHistoryEntry = parsingHistoryEntry;
  }

  public ParsingHistoryEntryDto getParsingHistoryEntry() {
    return this.parsingHistoryEntry;
  }
}
