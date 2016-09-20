package com.laputa.massager191.bean;


public class History {
	private String date;
	private int power;
	private String address;
	private int model;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public History() {
		super();
	}
	
	public History(String date, int power, int model) {
		super();
		this.date = date;
		this.power = power;
		this.model = model;
	}
	
	
	
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "History [date=" + date + ", power=" + power + ", address="
				+ address + ", model=" + model + "]";
	}
	
	
}
