package org.dynamo.applicantsapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_cart_answers")
public class ShoppingCartAnswer  implements Serializable {

	private static final long serialVersionUID = -5308881318604281587L;

	private int id;
	private String bugs;
	private String testCases;
	private String improvements;
	private int applicantId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "bugs")
	public String getBugs() {
		return bugs;
	}
	public void setBugs(String bugs) {
		this.bugs = bugs;
	}
	
	@Column(name = "improvements")
	public String getImprovements() {
		return improvements;
	}
	
	public void setImprovements(String improvements) {
		this.improvements = improvements;
	}
	
    @Column(name = "applicant_id")
	public int getApplicantId() {
		return applicantId;
	}
    
	@Column(name = "test_cases")
	public String getTestCases() {
		return testCases;
	}

	public void setTestCases(String testCases) {
		this.testCases = testCases;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}
	

	
	

}
