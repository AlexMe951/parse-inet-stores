package com.alexme951.parseinetstores.repository.dto.parsing;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_link")
public class ProductLinkParsingDto extends BasicParsingDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime parsingTime;
  private String linkUrl;

  public ProductLinkParsingDto() {
    super();
  }

  public ProductLinkParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      Long id1, LocalDateTime parsingTime, String linkUrl) {
    super(id, parsingHistoryEntry);
    this.id = id1;
    this.parsingTime = parsingTime;
    this.linkUrl = linkUrl;
  }
}
