package com.bigdata.mongodb;
 
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bigdata.mongodb.domain.CandidateSummaryResult;
import com.bigdata.mongodb.miner.DataMiner;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-config.xml")
public class MongoDBSpringDataTest {
 
	@Autowired
	private DataMiner dataMiner;
 
	@Test
	public void testGetTotalCount() {
		long count = dataMiner.getTotalCount();
		System.out.println();
		System.out.format("Total Record Count %d ", count);
		System.out.println();
		System.out.println();
		Assert.assertTrue(count > 0);
	}
 
	@Test
	public void testGetCountByCandidateName() {
		String name = "Bachmann, Michelle";
		long count = dataMiner.getTotalCount(name);
		System.out.println();
		System.out.format("Transaction count for %s is %d", name, count);
		System.out.println();
		System.out.println();
		Assert.assertTrue(count > 0);
	}
 
	@Test
	public void getCandidateSummary() {
		MapReduceResults<CandidateSummaryResult> results = dataMiner
				.getContributions();
		for (CandidateSummaryResult result : results) {
			System.out.println(result);
		}
		Assert.assertTrue(results != null);
		Assert.assertTrue(results.getCounts().getOutputCount() > 0);
	}
 
	public DataMiner getDataMiner() {
		return dataMiner;
	}
 
	public void setDataMiner(DataMiner dataMiner) {
		this.dataMiner = dataMiner;
	}
}

