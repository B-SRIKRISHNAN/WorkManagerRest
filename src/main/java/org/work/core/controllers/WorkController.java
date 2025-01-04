package org.work.core.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.work.core.exceptions.WorkManagerException;
import org.work.core.models.Work;
import org.work.core.models.WorkError;
import org.work.core.services.WorkService;

@RequestMapping("/work")
@RestController
public class WorkController {

	@Autowired
	private WorkService workService;


	public WorkController(WorkService workService) {
		this.workService = workService;
	}
	
	@PostMapping("/addWork")
	public ResponseEntity<?> addWork(@RequestBody Work work) throws WorkManagerException, SQLException {
		work.setLastWorkedOn(new Timestamp(System.currentTimeMillis()));
		work.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		System.out.println(work);
		WorkError errors = new WorkError();
//		Errors errors = new org.springframework.validation.BeanPropertyBindingResult(work, "work");
		List<String> errorStr = new java.util.ArrayList<>();
		Work workAdded = workService.addWork(work, errorStr);
		if(errorStr.size()>0) {
			errors.setErrors(errorStr);
			errors.setStatusCode(500);
			errors.setMessage("Error adding new work");
//			throw new WorkManagerException("exception");
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.ok(workAdded);
	}
	
	@GetMapping("/getWork")
	public List<Work> getWork(@RequestBody Work work){
		return workService.getWorks();
	}
}
