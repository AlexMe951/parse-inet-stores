package com.alexme951.parseinetstores.repository.dto.goldenrecords;

import com.alexme951.parseinetstores.repository.Seller;
import com.alexme951.parseinetstores.repository.dto.BasicDto;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "catalog_code")
public class CatalogCodeDto extends BasicDto {
  @Valid
  private Seller seller;

  private String code;

  public CatalogCodeDto() {
    super();
  }

  public CatalogCodeDto(Seller seller, String code) {
    this.seller = seller;
    this.code = code;
  }
}
