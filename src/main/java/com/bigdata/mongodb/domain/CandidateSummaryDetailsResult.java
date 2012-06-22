package com.bigdata.mongodb.domain;

import java.text.DecimalFormat;

public class CandidateSummaryDetailsResult {
	private long count;
	private String candNm;
	private double amt;

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getCandNm() {
		return candNm;
	}

	public void setCandNm(String candNm) {
		this.candNm = candNm;
	}

	@Override
	public String toString() {
		DecimalFormat fmt = new DecimalFormat();
		;
		return "[count=" + count + ", amt=" + fmt.format(amt) + "]";
	}

}
