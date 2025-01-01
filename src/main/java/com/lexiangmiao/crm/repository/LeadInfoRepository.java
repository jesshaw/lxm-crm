package com.lexiangmiao.crm.repository;

import com.lexiangmiao.crm.domain.LeadInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LeadInfo entity.
 */
@Repository
public interface LeadInfoRepository extends JpaRepository<LeadInfo, Long>, JpaSpecificationExecutor<LeadInfo> {
    @Query("select leadInfo from LeadInfo leadInfo where leadInfo.assignedUser.login = ?#{authentication.name}")
    List<LeadInfo> findByAssignedUserIsCurrentUser();

    default Optional<LeadInfo> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<LeadInfo> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<LeadInfo> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select leadInfo from LeadInfo leadInfo left join fetch leadInfo.reportsTo left join fetch leadInfo.assignedUser",
        countQuery = "select count(leadInfo) from LeadInfo leadInfo"
    )
    Page<LeadInfo> findAllWithToOneRelationships(Pageable pageable);

    @Query("select leadInfo from LeadInfo leadInfo left join fetch leadInfo.reportsTo left join fetch leadInfo.assignedUser")
    List<LeadInfo> findAllWithToOneRelationships();

    @Query(
        "select leadInfo from LeadInfo leadInfo left join fetch leadInfo.reportsTo left join fetch leadInfo.assignedUser where leadInfo.id =:id"
    )
    Optional<LeadInfo> findOneWithToOneRelationships(@Param("id") Long id);
}
