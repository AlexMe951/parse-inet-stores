package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.ProductUnitType;
import com.alexme951.parseinetstores.repository.dto.ParsingDto;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "price_parsing")
public class PricesParsingDto extends ParsingDto {

  @OneToOne
  ProductParsingDto product;

  @NotNull
  Integer priceInKopeyka;

  @NotNull
  ProductUnitType unitType;

  Integer amount;

  Boolean isAction;

  Double discount;

  public PricesParsingDto(
      ParsingHistoryEntryDto parsingHistoryEntry,
      ProductParsingDto product,
      @NotNull Integer priceInKopeyka,
      @NotNull ProductUnitType unitType,
      Integer amount,
      Boolean isAction,
      Double discount) {
    super(parsingHistoryEntry);
    this.product = product;
    this.priceInKopeyka = priceInKopeyka;
    this.unitType = unitType;
    this.amount = amount;
    this.isAction = isAction;
    this.discount = discount;
  }

  public PricesParsingDto() {
    super();
  }
}
