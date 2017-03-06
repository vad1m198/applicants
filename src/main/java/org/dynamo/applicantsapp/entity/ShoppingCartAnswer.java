package org.dynamo.applicantsapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_cart_answers")
public class ShoppingCartAnswer  implements Serializable {

	private static final long serialVersionUID = -5308881318604281587L;

	private int id;
	private String bugs;
	private String test_cases;
	private String improvements;
	private int applicant_id;
	
	@Id
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

	@Column(name = "test_cases")
	public String getTest_cases() {
		return test_cases;
	}

	public void setTest_cases(String test_cases) {
		this.test_cases = test_cases;
	}
    @Column(name = "applicant_id")
	public int getApplicant_id() {
		return applicant_id;
	}

	public void setApplicant_id(int applicant_id) {
		this.applicant_id = applicant_id;
	}
	

	
	

}
