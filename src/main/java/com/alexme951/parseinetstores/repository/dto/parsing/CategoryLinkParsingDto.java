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
@Table(name = "category_link")
public class CategoryLinkParsingDto extends BasicDto {

  @NotNull
  private String linkUrl;

  @NotNull
  private String urlPostfix;

  @OneToOne(fetch = FetchType.LAZY)
  CategoryParsingDto category;

  @OneToOne(fetch = FetchType.LAZY)
  private CategoryLinkParsingDto parentCategoryLink;

  @OneToOne
  @NotNull
  private ParsingHistoryEntryDto parsingHistoryEntry;

  public CategoryLinkParsingDto() {
    super();
  }

  public CategoryLinkParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry,
      @NotNull String linkUrl, @NotNull String urlPostfix,
      CategoryParsingDto category,
      CategoryLinkParsingDto parentCategoryLink) {
    super(id);
    this.parsingHistoryEntry = parsingHistoryEntry;
    this.linkUrl = linkUrl;
    this.urlPostfix = urlPostfix;
    this.category = category;
    this.parentCategoryLink = parentCategoryLink;
  }

  public void setParsingHistoryEntry(ParsingHistoryEntryDto parsingHistoryEntry) {
    this.parsingHistoryEntry = parsingHistoryEntry;
  }

  public ParsingHistoryEntryDto getParsingHistoryEntry() {
    return this.parsingHistoryEntry;
  }
}
