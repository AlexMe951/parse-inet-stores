package com.alexme951.parseinetstores.repository.dto;

import com.alexme951.parseinetstores.repository.dto.parsing.ParsingHistoryEntryDto;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class ParsingDto extends BasicDto {

  @OneToOne
  @NotNull
  private ParsingHistoryEntryDto parsingHistoryEntry;

  public ParsingDto(ParsingHistoryEntryDto parsingHistoryEntry) {
    this.parsingHistoryEntry = parsingHistoryEntry;
  }

  public ParsingDto() { super();}
}
