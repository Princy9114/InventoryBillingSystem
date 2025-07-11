package com.inventory.model;

public class Company {
	private int id;
	private String name;
	private String address;
	private String contact;
	private boolean status;
	
	public Company() {
	}

	public Company(int id, String name, String address, String contact) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
