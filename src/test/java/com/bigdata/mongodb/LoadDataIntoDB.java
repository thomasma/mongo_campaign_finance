package com.bigdata.mongodb;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigdata.mongodb.miner.DataMiner;

/**
 * This should be a one time action. So its a separate class for now and not a
 * JUnit test.
 */
public class LoadDataIntoDB {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		DataMiner data = ctx.getBean(DataMiner.class);

		// --------------------------------
		// load data
		// --------------------------------
		data.loadData(new File("/home/mathew/temp/P00000001-ALL.csv"));

		// --------------------------------
		// print total count for verification
		// --------------------------------
		System.out
				.println("Total Count of Documents = " + data.getTotalCount());
	}
}
