package com.alexme951.parseinetstores.service.dto;

import java.time.OffsetDateTime;
import java.util.Date;

public class CategoryLink {
  private String linkUrl;
  private OffsetDateTime parsingTime;
  private CategoryLink categoryLink;

  public CategoryLink(String linkUrl) {
    this.linkUrl = linkUrl;
    this.parsingTime = OffsetDateTime.now();
  }

  public String getLinkUrl() {
    return linkUrl;
  }

  public OffsetDateTime getParsingTime() {
    return parsingTime;
  }

  public CategoryLink(String linkUrl, OffsetDateTime parsingTime) {
    this.linkUrl = linkUrl;
    this.parsingTime = parsingTime;
  }

  public CategoryLink getCategoryLink() {
    return categoryLink;
  }

  public void setCategoryLink(CategoryLink categoryLink) {
    this.categoryLink = categoryLink;
  }
}
