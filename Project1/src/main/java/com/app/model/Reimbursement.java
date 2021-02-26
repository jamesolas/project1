package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "reimbursement", schema="Project1")
public class Reimbursement {
	@Id
	@GeneratedValue(generator = "id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "id_seq", sequenceName = "project1.reimbursement_request_id_seq")
	@Column (name="request_id")
	private int requestId;
	
	@Column (name="employee_id")
	private int employeeId;
	
	
	@Column (name="manager_id")
	private int managerId;
	
	@Column
	private String status;
	
	@Column
	private double amount;
	
	@Column
	private Date date;
	
	
	public Reimbursement() {
		super();
	}


	public Reimbursement(int requestId, int employeeId, int managerId, String status, double amount, Date date) {
		super();
		this.requestId = requestId;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.status = status;
		this.amount = amount;
		this.date = date;
	}


	public int getRequestId() {
		return requestId;
	}


	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + employeeId;
		result = prime * result + managerId;
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (managerId != other.managerId)
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
				+ ", status=" + status + ", amount=" + amount + ", date=" + date + "]";
	}


				
	
}
