package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Reimbursement {
	@Id
	@Column (name="request_id")
	private int requestId;
	
	@ManyToOne
	@JoinColumn (name="employee_id")
	private Employee employeeId;
	
	@Column (name="manager_id")
	private int managerId;
	
	@Column
	private String status;
	
	@Column
	private double amount;
	
	@ManyToOne
	@JoinColumn
	private Employee managersId;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int requestId, Employee employeeId, int managerId, String status, double amount,
			Employee managersId) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.status = status;
		this.amount = amount;
		this.managersId = managersId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Employee getManagersId() {
		return managersId;
	}

	public void setManagersId(Employee managersId) {
		this.managersId = managersId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + managerId;
		result = prime * result + ((managersId == null) ? 0 : managersId.hashCode());
		result = prime * result + requestId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (managerId != other.managerId)
			return false;
		if (managersId == null) {
			if (other.managersId != null)
				return false;
		} else if (!managersId.equals(other.managersId))
			return false;
		if (requestId != other.requestId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [requestId=" + requestId + ", employeeId=" + employeeId + ", managerId=" + managerId
				+ ", status=" + status + ", amount=" + amount + ", managersId=" + managersId + "]";
	}

		

}
