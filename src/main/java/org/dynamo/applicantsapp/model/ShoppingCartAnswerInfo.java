package org.dynamo.applicantsapp.model;

public class ShoppingCartAnswerInfo {
	
	private int id;
	private String bugs;
	private String test_cases;
	private String improvements;
	private int applicant_id;
	
	public ShoppingCartAnswerInfo() {
		
	}
	
	
	public ShoppingCartAnswerInfo(int id, String bugs, String testCases, String improvements, int applicantId) {
		this.id = id;
		this.bugs = bugs;
		this.test_cases = testCases;
		this.improvements = improvements;
		this.applicant_id = applicantId;
	}
	
	public ShoppingCartAnswerInfo(String bugs, String testCases, String improvements, int applicantId) {		
		this.bugs = bugs;
		this.test_cases = testCases;
		this.improvements = improvements;
		this.applicant_id = applicantId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBugs() {
		return bugs;
	}
	public void setBugs(String bugs) {
		this.bugs = bugs;
	}
	public String getImprovements() {
		return improvements;
	}
	public void setImprovements(String improvements) {
		this.improvements = improvements;
	}


	public String getTest_cases() {
		return test_cases;
	}

	public void setTest_cases(String test_cases) {
		this.test_cases = test_cases;
	}


	public int getApplicant_id() {
		return applicant_id;
	}


	public void setApplicant_id(int applicant_id) {
		this.applicant_id = applicant_id;
	}
}
