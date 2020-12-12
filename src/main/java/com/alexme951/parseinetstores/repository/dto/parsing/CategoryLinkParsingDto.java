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

  @NotNull
  private String urlPostfix;

  @OneToOne
  CategoryParsingDto category;

  @OneToOne(fetch = FetchType.LAZY)
  private CategoryLinkParsingDto parentCategoryLink;

  public CategoryLinkParsingDto() {
    super();
  }

  public CategoryLinkParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      @NotNull String linkUrl, @NotNull String urlPostfix,
      CategoryParsingDto category,
      CategoryLinkParsingDto parentCategoryLink) {
    super(id, parsingHistoryEntry);
    this.linkUrl = linkUrl;
    this.urlPostfix = urlPostfix;
    this.category = category;
    this.parentCategoryLink = parentCategoryLink;
  }
}
