
package org.work.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/**
 * This is a model describing the status of a work.
 */
@Entity
public class WorkStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_status_id_seq")
	@SequenceGenerator(sequenceName = "work_status_id_seq", name = "work_status_id_seq", allocationSize = 1)
	private int statusId;

	@Column
	private String statusCode;

	@Column
	private String statusName;

	@Column
	private String statusDesc;

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public WorkStatus(int statusId) {
		this.statusId = statusId;
	}

	public WorkStatus() {
		super();
	}


}
