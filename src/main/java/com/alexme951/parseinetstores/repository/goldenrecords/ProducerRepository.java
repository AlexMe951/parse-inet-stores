package com.alexme951.parseinetstores.repository.goldenrecords;

import com.alexme951.parseinetstores.repository.dto.goldenrecords.ProducerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends CrudRepository<ProducerDto, Long> {

}
