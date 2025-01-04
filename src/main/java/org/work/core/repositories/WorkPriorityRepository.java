/**
 * 
 */
package org.work.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.core.models.WorkPriority;

public interface WorkPriorityRepository extends JpaRepository<WorkPriority, Integer> {

}
