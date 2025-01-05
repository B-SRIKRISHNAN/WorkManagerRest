package org.work.core.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Work {

	@Id
	@SequenceGenerator(sequenceName = "work_id_seq", name = "work_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "work_id_seq", strategy = GenerationType.SEQUENCE)
	private int workId;
	
	@Column
	private String workName;

	@Column
	private String workDesc;

	@Column
	private String workLocation;
	
	@ManyToOne
	@JoinColumn(name = "work_status_id", nullable=false)
	private WorkStatus workStatus;

	@Column
	private Timestamp createdAt;
	
	@Column
	private Timestamp lastWorkedOn;

	@ManyToOne
	@JoinColumn(name = "work_type_id", nullable=false)
	private WorkType workType;

	@ManyToOne
	@JoinColumn(name = "work_priority_id", nullable=false)
	private WorkPriority workPriority;

	
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getWorkId() {
		return workId;
	}

	public void setWorkId(int workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public WorkStatus getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(WorkStatus workStatus) {
		this.workStatus = workStatus;
	}

	public Timestamp getLastWorkedOn() {
		return lastWorkedOn;
	}

	public void setLastWorkedOn(Timestamp lastWorkedOn) {
		this.lastWorkedOn = lastWorkedOn;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public WorkPriority getWorkPriority() {
		return workPriority;
	}

	public void setWorkPriority(WorkPriority workPriority) {
		this.workPriority = workPriority;
	}

	@Override
	public String toString() {
		return "Work [workId=" + workId + ", workName=" + workName + ", workDesc=" + workDesc + ", workLocation="
				+ workLocation + ", workStatus=" + workStatus.getStatusName() + ", createdAt=" + createdAt+ ", lastWorkedOn=" + lastWorkedOn + ", workType="
				+ workType.getTypeName() + ", workPriority=" + workPriority.getPriorityName() + "]";
	}
	
	

}
