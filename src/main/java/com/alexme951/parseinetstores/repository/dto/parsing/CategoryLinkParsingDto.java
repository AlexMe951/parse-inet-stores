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
@Table(name = "category_link")
public class CategoryLinkParsingDto extends ParsingDto {

  @NotNull
  private String linkUrl;

  @OneToOne(fetch = FetchType.LAZY)
  private CategoryLinkParsingDto parentCategoryLink;

  public CategoryLinkParsingDto(
      @NotNull String linkUrl,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      CategoryLinkParsingDto parentCategoryLink) {
    super(parsingHistoryEntry);
    this.linkUrl = linkUrl;
    this.parentCategoryLink = parentCategoryLink;
  }

  public CategoryLinkParsingDto() {
    super();
  }
}
