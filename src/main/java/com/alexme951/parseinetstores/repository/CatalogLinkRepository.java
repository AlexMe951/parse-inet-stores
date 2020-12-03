package com.alexme951.parseinetstores.repository;

import com.alexme951.parseinetstores.repository.dto.CatalogLink;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogLinkRepository extends CrudRepository<CatalogLink, Long> {

  @Query("select cl from CatalogLink cl where cl.linkUrl like %?1%")
  List<CatalogLink> findAllByLinkUrl(String linkUrl);

}
