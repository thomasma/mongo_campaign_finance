package com.bigdata.mongodb.miner;

import java.io.File;

import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

import com.bigdata.mongodb.domain.SummaryResult;
import com.bigdata.mongodb.domain.CandidateSummaryResult;

public interface DataMiner {

	public void loadData(File dataFile) throws Exception;

	public long getTotalCount(String candidateName);

	public long getTotalCount();

	public MapReduceResults<CandidateSummaryResult> getContributions();

	public MapReduceResults<SummaryResult> getSummary();

}