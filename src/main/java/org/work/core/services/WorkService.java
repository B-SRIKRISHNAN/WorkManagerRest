package org.work.core.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.work.core.exceptions.WorkManagerException;
import org.work.core.models.Work;
import org.work.core.repositories.WorkPriorityRepository;
import org.work.core.repositories.WorkRepository;
import org.work.core.repositories.WorkStatusRepository;
import org.work.core.repositories.WorkTypeRepository;

@Service
public class WorkService {

	private WorkRepository workRepository;
	private WorkStatusRepository workStatusRepository;
	private WorkTypeRepository workTypeRepository;
	private WorkPriorityRepository workPriorityRepository;

	public WorkService(WorkRepository workRepository, WorkStatusRepository workStatusRepository,
			WorkTypeRepository workTypeRepository, WorkPriorityRepository workPriorityRepository) {
		super();
		this.workRepository = workRepository;
		this.workStatusRepository = workStatusRepository;
		this.workTypeRepository = workTypeRepository;
		this.workPriorityRepository = workPriorityRepository;
	}

	public Work addWork(Work work, List<String> errors ) throws WorkManagerException, SQLException {
		validateNewWork(work, errors);
		if(errors.size()>0) {
			return null;
		}
		return workRepository.save(work);
	}
	
	private void validateNewWork(Work work, List<String> errors) throws WorkManagerException, SQLException {
	
		
		if("".equals(work.getWorkName())){
			errors.add("Work Name cannot be empty");
		}else if(workRepository.findByWorkName(work.getWorkName()) != null) {
			errors.add("Work Name already exists");
		}
		
		if("".equals(work.getWorkDesc())){
			errors.add("Work Description cannot be empty");
		}
		
		if("".equals(work.getWorkLocation())){
			errors.add("Work Location cannot be empty");
		}
		
		if(work.getLastWorkedOn() == null) {
			errors.add("Last Worked On cannot be empty");
		}
		
		if(workPriorityRepository.findById(work.getWorkPriority().getPriorityId()).isEmpty()) {
			errors.add("Work Priority not found");
		}
		
		if(workTypeRepository.findById(work.getWorkType().getTypeId()).isEmpty()) {
			errors.add("Work Type not found");
		}
		
		if(workStatusRepository.findById(work.getWorkStatus().getStatusId()).isEmpty()) {
			errors.add("Work Status not found");
		}
	}

	// get all work details
	public List<Work> getWorks() {
		return workRepository.findAll();
	}

}
