package com.alexme951.parseinetstores.repository.dto.parsing;

import com.alexme951.parseinetstores.repository.dto.BasicDto;
import java.time.OffsetDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parsing_history")
public class ParsingHistoryEntryDto extends BasicDto {

  @NotNull
  private OffsetDateTime parsingTime;

  public ParsingHistoryEntryDto(@NotNull OffsetDateTime parsingTime) {
    this.parsingTime = parsingTime;
  }

  public ParsingHistoryEntryDto() {
    super();
  }
}
