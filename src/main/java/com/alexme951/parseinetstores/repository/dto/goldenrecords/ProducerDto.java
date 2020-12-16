package com.alexme951.parseinetstores.repository.dto.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producer")
public class ProducerDto extends BasicDto {
  private String name;

  public ProducerDto() {
    super();
  }

  public ProducerDto(String name) {
    this.name = name;
  }
}
