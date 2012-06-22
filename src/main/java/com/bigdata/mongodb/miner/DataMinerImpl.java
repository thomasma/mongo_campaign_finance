package com.bigdata.mongodb.miner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.bigdata.mongodb.domain.Contribution;
import com.bigdata.mongodb.domain.SummaryResult;
import com.bigdata.mongodb.domain.CandidateSummaryResult;
import com.google.common.base.Splitter;

@Component
public class DataMinerImpl {
	@Autowired
	private MongoTemplate mongoTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bigdata.mongodb.DataMiner#loadData(java.io.File)
	 */
	public void loadData(File dataFile) throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader(dataFile));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				Iterable<String> cols = Splitter.on('|').trimResults()
						.split(line);
			
				java.util.List<String> list = new ArrayList<String>();
				for (String col : cols) {
					list.add(col);
				}
				Contribution c = new Contribution();
				c.setCmteId(list.get(0));
				c.setCandId(list.get(1));
				c.setCandNm(list.get(2));
				c.setContbrNm(list.get(3));
				c.setContbrCity(list.get(4));
				c.setContbrSt(list.get(5));
				c.setContbrZip(list.get(6));
				c.setContbrEmployer(list.get(7));
				c.setContbrOccupation(list.get(8));
				c.setContbReceiptAmt(Double.valueOf(list.get(9)));
				c.setContbReceiptDt(list.get(10));
				c.setReceiptDesc(list.get(11));
				c.setMemoCd(list.get(12));
				c.setMemoText(list.get(13));
				c.setFormTp(list.get(14));
				c.setFileNum(list.get(15));

				mongoTemplate.save(c);
			}
		} finally {
			reader.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bigdata.mongodb.DataMiner#getTotalCount(java.lang.String)
	 */
	public long getTotalCount(String candidateName) {
		return mongoTemplate.count(
				new Query(Criteria.where("candNm").is("Bachmann, Michelle")),
				Contribution.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bigdata.mongodb.DataMiner#getTotalCount()
	 */
	public long getTotalCount() {
		return mongoTemplate.count(new Query(), Contribution.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bigdata.mongodb.DataMiner#getContributions()
	 */
	public MapReduceResults<CandidateSummaryResult> getContributions() {
		return mongoTemplate.mapReduce("contribution",
				"classpath:map_by_candidate.js",
				"classpath:reduce_by_candidate.js",
				CandidateSummaryResult.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bigdata.mongodb.DataMiner#getSummary()
	 */
	public MapReduceResults<SummaryResult> getSummary() {
		return mongoTemplate.mapReduce("contribution",
				"classpath:map_summary.js", "classpath:reduce_summary.js",
				SummaryResult.class);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
}
