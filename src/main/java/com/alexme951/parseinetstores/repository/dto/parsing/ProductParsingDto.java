package com.alexme951.parseinetstores.repository.dto.parsing;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductParsingDto extends BasicParsingDto {

  private String name;
  private String description;
  private String code;
  private String attributes;

  public ProductParsingDto() {
    super();
  }

  public ProductParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      String name, String description, String code, String attributes) {
    super(id, parsingHistoryEntry);
    this.name = name;
    this.description = description;
    this.code = code;
    this.attributes = attributes;
  }
}
