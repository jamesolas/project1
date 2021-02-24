package com.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (schema="Project1")
public class EmployeeHierarchy {
	
	@OneToOne
	@JoinColumn	
	private Employee employeeId;
	
	@OneToOne
	@JoinColumn
	private Employee managerId;

	public EmployeeHierarchy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeHierarchy(Employee employeeId, Employee managerId) {
		super();
		this.employeeId = employeeId;
		this.managerId = managerId;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getManagerId() {
		return managerId;
	}

	public void setManagerId(Employee managerId) {
		this.managerId = managerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((managerId == null) ? 0 : managerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeHierarchy other = (EmployeeHierarchy) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (managerId == null) {
			if (other.managerId != null)
				return false;
		} else if (!managerId.equals(other.managerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeHierarchy [employeeId=" + employeeId + ", managerId=" + managerId + "]";
	}
	

}
