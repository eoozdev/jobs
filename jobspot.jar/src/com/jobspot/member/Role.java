package com.jobspot.member;

public enum Role {

	JOBSEEKER("1", "JOBSEEKER","/member/jobseeker/index.jsp"),
	EMPLOYER("2", "EMPLOYER","/member/employer/index.jsp"),
	NO_ROLE("-1","NO ROLE","/error.jsp");
	
	private String role;
	private String forwardURL;
	private String code;
	
	private Role(String code, String role, String forwardURL){
		this.code = code;
		this.role = role;
		this.forwardURL = forwardURL;
	}
	
	public String value(){
		return this.role;
	}
	
	public String forwardURL(){
		return this.forwardURL;
	}

	public String getRolecode() {
		return code;
	}
	
	public static Role fromString(String strng) {
		
		for(Role r : Role.values()){
			
			if(r.value().equalsIgnoreCase(strng))
				return r;
		}
		
		return NO_ROLE;
	}

}
