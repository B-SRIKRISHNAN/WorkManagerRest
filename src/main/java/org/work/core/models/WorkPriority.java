package org.work.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class WorkPriority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_priority_id_seq")
	@SequenceGenerator(sequenceName = "work_priority_id_seq", name = "work_priority_id_seq", allocationSize = 1)
	private int priorityId;

	@Column
	private String priorityName;

	@Column
	private String priorityCode;

	@Column
	private String priorityDesc;

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getPriorityCode() {
		return priorityCode;
	}

	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}

	public String getPriorityDesc() {
		return priorityDesc;
	}

	public void setPriorityDesc(String priorityDesc) {
		this.priorityDesc = priorityDesc;
	}

	// constructor for id only
	public WorkPriority(int priorityId) {
		this.priorityId = priorityId;
	}
	
	//default constructor
	public WorkPriority() {
		
	}
}
