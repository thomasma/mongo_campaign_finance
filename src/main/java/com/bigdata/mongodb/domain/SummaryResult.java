package com.bigdata.mongodb.domain;

public class SummaryResult {
	private long lessThanEq200;
	private String key;

	public long getLessThanEq200() {
		return lessThanEq200;
	}

	public void setLessThanEq200(long lessThanEq200) {
		this.lessThanEq200 = lessThanEq200;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "SummaryResult [lessThanEq200=" + lessThanEq200 + ", key=" + key
				+ "]";
	}
}
