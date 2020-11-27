package com.alexme951.parseinetstores.repository.dto;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CatalogLink {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime parsingTime;
  private String linkUrl;

  public CatalogLink(Long id, LocalDateTime parsingTime, String linkUrl) {
    this.id = id;
    this.parsingTime = parsingTime;
    this.linkUrl = linkUrl;
  }

  public CatalogLink() {
  }

  @Override
  public String toString() {
    return "CatalogLink{" +
        "id=" + id +
        ", parsingTime=" + parsingTime +
        ", linkUrl='" + linkUrl + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CatalogLink)) {
      return false;
    }

    CatalogLink that = (CatalogLink) o;

    if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) {
      return false;
    }
    if (!getParsingTime().equals(that.getParsingTime())) {
      return false;
    }
    return getLinkUrl().equals(that.getLinkUrl());
  }

  @Override
  public int hashCode() {
    int result = getId() != null ? getId().hashCode() : 0;
    result = 31 * result + getParsingTime().hashCode();
    result = 31 * result + getLinkUrl().hashCode();
    return result;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setParsingTime(LocalDateTime parsingTime) {
    this.parsingTime = parsingTime;
  }

  public void setLinkUrl(String linkUrl) {
    this.linkUrl = linkUrl;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getParsingTime() {
    return parsingTime;
  }

  public String getLinkUrl() {
    return linkUrl;
  }
}
