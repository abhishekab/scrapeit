package com.samagra.ab.scrapeit.common.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
class MongoConfiguration extends AbstractMongoClientConfiguration {

	// TODO: Move to config file
	private static final String DB_NAME = "courses_db";

	@Override
	protected String getDatabaseName() {
		return DB_NAME;
	}

}
