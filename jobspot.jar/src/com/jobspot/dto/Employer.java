package com.jobspot.dto;

public class Employer {

	private String code;
	private String name;
	private String user;
	private String town;
	private String logo;
	private String type;
	private String about;
	private String status;
	private String industry;
	private String companySize;
	private String contactEmail;
	private String contactNumber;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCompanySize() {
		return companySize;
	}
	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}
	
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setContactEmail(String string) {
		this.contactEmail = string;
	}
	
	public String getContactemail(){
		return this.contactEmail;
	}
	
	
	public void setContactnumber(String string) {
		this.contactNumber = string;
		
	}
	
	public String getContactnumber(){
		return this.contactNumber;
	}
	
}
