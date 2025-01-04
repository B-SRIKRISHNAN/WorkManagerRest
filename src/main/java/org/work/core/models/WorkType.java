package org.work.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class WorkType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_type_id_seq")
	@SequenceGenerator(sequenceName = "work_type_id_seq", name = "work_type_id_seq", allocationSize = 1)
	private int typeId;

	@Column
	private String typeName;

	@Column
	private String typeCode;

	@Column
	private String typeDesc;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public WorkType(int typeId) {
		this.typeId = typeId;
	}
	
	//default constructor

	public WorkType() {
	}
}