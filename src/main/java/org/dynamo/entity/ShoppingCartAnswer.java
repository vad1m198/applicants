package org.dynamo.entity;

public class ShoppingCartAnswer {

	private long id;
	private String bugs;
	private String ideas;
	private String improvements;
	private boolean submitted;
	private long userId;
	
	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getIdeas() {
		return ideas;
	}

	public void setIdeas(String ideas) {
		this.ideas = ideas;
	}
	
	

}
