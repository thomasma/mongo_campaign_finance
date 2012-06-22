package com.bigdata.mongodb.domain;

public class CandidateSummaryResult {

	private String id;
	private CandidateSummaryDetailsResult value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CandidateSummaryDetailsResult getValue() {
		return value;
	}

	public void setValue(CandidateSummaryDetailsResult value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CandidateSummaryResult [id=" + id + value + "]";
	}

}
