package com.health.coverage_service.bmi;

import lombok.Getter;
import lombok.Setter;


public class BMIRequest {
	private double height;
	private double weight;
	private String unit;
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
