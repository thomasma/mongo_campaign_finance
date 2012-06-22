package com.bigdata.mongodb.miner;

import java.io.File;

import org.aver.fft.RecordListener;
import org.aver.fft.Transformer;
import org.aver.fft.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.bigdata.mongodb.domain.CandidateSummaryResult;
import com.bigdata.mongodb.domain.Contribution;
import com.bigdata.mongodb.domain.SummaryResult;

@Component
public class LoadDataToMongoDBImpl implements DataMiner {
	@Autowired
	private MongoTemplate mongoTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bigdata.mongodb.DataMiner#loadData(java.io.File)
	 */
	public void loadData(File dataFile) throws Exception {
		Transformer spec = TransformerFactory
				.getTransformer(Contribution.class);
		spec.parseFlatFile(dataFile, new RecordListener() {
			public boolean foundRecord(Object o) {
				Contribution bean = (Contribution) o;
				// System.out.println( bean.toString());
				mongoTemplate.save(bean);
				return true;
			}

			public boolean unresolvableRecord(String rec) {
				// nothing in here for now
				return true;
			}
		});
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
