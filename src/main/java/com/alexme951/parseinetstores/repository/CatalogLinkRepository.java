package com.alexme951.parseinetstores.repository;

import com.alexme951.parseinetstores.repository.dto.CatalogLink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogLinkRepository extends CrudRepository<CatalogLink, Long> {

}
