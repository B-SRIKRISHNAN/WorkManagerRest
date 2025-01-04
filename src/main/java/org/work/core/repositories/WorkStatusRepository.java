package org.work.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.core.models.WorkStatus;

public interface WorkStatusRepository extends JpaRepository<WorkStatus, Integer> {

}
