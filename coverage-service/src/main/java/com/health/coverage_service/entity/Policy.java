package com.health.coverage_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	 private String policyNumber;
	 private String policyHolder;
	 private String coverageType;
	 private double sumInsured;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getPolicyHolder() {
		return policyHolder;
	}
	public void setPolicyHolder(String policyHolder) {
		this.policyHolder = policyHolder;
	}
	public String getCoverageType() {
		return coverageType;
	}
	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}
	public double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}
}
