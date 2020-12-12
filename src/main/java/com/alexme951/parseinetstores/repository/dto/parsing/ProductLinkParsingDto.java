package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.dto.ParsingDto;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_link")
public class ProductLinkParsingDto extends ParsingDto {

  private String linkUrl;

  public ProductLinkParsingDto() {
    super();
  }

  public ProductLinkParsingDto(Long id,
      ParsingHistoryEntryDto parsingHistoryEntry,
      Long id1,
      String linkUrl) {
    super(id, parsingHistoryEntry);
    this.linkUrl = linkUrl;
  }
}
