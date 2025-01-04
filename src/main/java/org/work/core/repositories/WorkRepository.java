package org.work.core.repositories;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.core.models.Work;
import org.work.core.models.WorkPriority;
import org.work.core.models.WorkStatus;
import org.work.core.models.WorkType;

public interface WorkRepository extends JpaRepository<Work, Integer> {

	Work findByWorkName(String workName) throws SQLException;
	
	List<Work> findByWorkStatusAndWorkTypeAndWorkPriorityAndWorkNameAndWorkDesc(WorkStatus workStatus, WorkType workType, WorkPriority workPriority, String workName, String workDesc) throws SQLException;
}
