package com.jobspot.member;

public enum Role {

	JOBSEEKER("1", "JOBSEEKER","/member/jobseeker/index.jsp", "/member/jobseeker/profile.jsp"),
	EMPLOYER("2", "EMPLOYER","/member/employer/index.jsp", "/member/employer/profile.jsp"),
	NO_ROLE("-1","NO ROLE","/error.jsp", "/error.jsp");
	
	private String role;
	private String onLoginURl;
	private String code;
	private String onSignUpUrl;
	
	private Role(String code, String role, String onLoginURl, String onSignUpUrl){
		this.code = code;
		this.role = role;
		this.onLoginURl = onLoginURl;
		this.onSignUpUrl = onLoginURl;
	}
	
	public String value(){
		return this.role;
	}
	
	public String forwardURL(){
		return this.onLoginURl;
	}
	
	public String onSignUpUrl(){
		return onSignUpUrl;
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
