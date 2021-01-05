package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.dto.ParsingDto;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductParsingDto extends ParsingDto {

  private String name;
  private String description;
  private String code;
  private String attributes; // JSON formatted key-value pairs of attributes

  public ProductParsingDto(
      ParsingHistoryEntryDto parsingHistoryEntry,
      String name,
      String description,
      String code,
      String attributes) {
    super(parsingHistoryEntry);
    this.name = name;
    this.description = description;
    this.code = code;
    this.attributes = attributes;
  }

  public ProductParsingDto() {
    super();
  }
}
