//package com.app.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table (name="employee_hierarchy", schema="Project1")
//public class EmployeeHierarchy implements Serializable {
//	@Id
//	@OneToOne
//	@JoinColumn
//	@GeneratedValue (generator = "hierarchy_employee_id_seq", strategy = GenerationType.AUTO)
//	@SequenceGenerator (allocationSize = 1, name = "hierarchy_employee_id_seq")
//	private Employee employeeId;
//	
//	@OneToOne
//	@JoinColumn
//	private Employee managerId;
//
//	public EmployeeHierarchy() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public EmployeeHierarchy(Employee employeeId, Employee managerId) {
//		super();
//		this.employeeId = employeeId;
//		this.managerId = managerId;
//	}
//
//	public Employee getEmployeeId() {
//		return employeeId;
//	}
//
//	public void setEmployeeId(Employee employeeId) {
//		this.employeeId = employeeId;
//	}
//
//	public Employee getManagerId() {
//		return managerId;
//	}
//
//	public void setManagerId(Employee managerId) {
//		this.managerId = managerId;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
//		result = prime * result + ((managerId == null) ? 0 : managerId.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		EmployeeHierarchy other = (EmployeeHierarchy) obj;
//		if (employeeId == null) {
//			if (other.employeeId != null)
//				return false;
//		} else if (!employeeId.equals(other.employeeId))
//			return false;
//		if (managerId == null) {
//			if (other.managerId != null)
//				return false;
//		} else if (!managerId.equals(other.managerId))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "EmployeeHierarchy [employeeId=" + employeeId + ", managerId=" + managerId + "]";
//	}
//	
//
//}
