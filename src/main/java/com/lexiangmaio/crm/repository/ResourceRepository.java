package com.lexiangmaio.crm.repository;

import com.lexiangmaio.crm.domain.Resource;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Resource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findAllByAuthorityNameIn(List<String> authorities);
}
