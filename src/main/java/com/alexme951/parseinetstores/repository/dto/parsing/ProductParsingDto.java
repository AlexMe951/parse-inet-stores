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
  private String attributes;

  public ProductParsingDto() {
    super();
  }

  public ProductParsingDto(Long id,
      ParsingHistoryEntryDto parsingHistoryEntry,
      String name,
      String description,
      String code,
      String attributes) {
    super(id, parsingHistoryEntry);
    this.name = name;
    this.description = description;
    this.code = code;
    this.attributes = attributes;
  }
}
