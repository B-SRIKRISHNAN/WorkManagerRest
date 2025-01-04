package org.work.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.core.models.WorkType;

public interface WorkTypeRepository extends JpaRepository<WorkType, Integer> {

}
