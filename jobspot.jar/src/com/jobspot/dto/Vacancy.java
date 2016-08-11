package com.jobspot.dto;

public class Vacancy {
	
	private String code;
	private String basic;
	private String title;
	private String artwork;
	private String displayTo;
	private String displayFrom;
	
	private String jobcategory;
	private String town;
	private String employer;
	
	
	public void setBasic(String value){
		this.basic = value;
	}
	
	
	public String getBasic(){
		return this.basic;
	}

	public void setTitle(String value){
		this.title = value;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setArtwork(String value){
		this.artwork = value;
	}
	
	public String getArtwork(){
		return this.artwork;
				
	}

	public void setDisplayfrom(String value){
		this.displayFrom = value;
	}
	
	public String getDisplayfrom(){
		return this.displayFrom;
	}
	
	public void setDisplayto(String value){
		this.displayTo = value;
	}
	
	public String getDisplayto(){
		return this.displayTo;
	}
	
	public void setTown(String value){
		this.town = value;
	}
	
	public String getTown(){
		return this.town;
	}
	
	public void setJobcategory(String value){
		this.jobcategory = value;
	}
	
	public String getJobcategory(){
		return this.jobcategory;
	}
	
	public void setEmployer(String value){
		this.employer = value;
	}
	
	public String getEmployer(){
		return this.employer;
	}


	public String getCode() {
		return this.code;
	}


	public void setCode(String string) {
		this.code = string;
	}
}
