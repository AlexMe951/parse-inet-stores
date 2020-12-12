package com.alexme951.parseinetstores.repository.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BasicDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public BasicDto() {
  }

  public BasicDto(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (!obj.getClass().equals(this.getClass())) {
      return false;
    }
    if (this.id == null || ((BasicDto) obj).id == null) {
      return false;
    }
    return this.id.equals(((BasicDto) obj).id);
  }
}


