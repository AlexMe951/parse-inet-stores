package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BasicParsingDto extends BasicDto {

  @OneToOne(fetch = FetchType.LAZY)
  @NotNull
  private ParsingHistoryEntryDto parsingHistoryEntry;

  public BasicParsingDto() {
    super();
  }

  public BasicParsingDto(Long id,
      @NotNull ParsingHistoryEntryDto parsingHistoryEntry) {
    super(id);
    this.parsingHistoryEntry = parsingHistoryEntry;
  }
}


