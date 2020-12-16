package com.alexme951.parseinetstores.repository.dto.goldenrecords;

import com.alexme951.parseinetstores.repository.Seller;
import com.alexme951.parseinetstores.repository.dto.BasicDto;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class BrandDto extends BasicDto {

  private String name;

  @OneToOne
  private ProducerDto producer;

  private Boolean isPrivateMark;

  private Seller privateMarkOwner;

  public BrandDto() {
    super();
  }

  public BrandDto(String name,
      ProducerDto producer,
      Boolean isPrivateMark,
      Seller privateMarkOwner) {
    this.name = name;
    this.producer = producer;
    this.isPrivateMark = isPrivateMark;
    this.privateMarkOwner = privateMarkOwner;
  }
}
