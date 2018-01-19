package com.gamaset.rentonator.infra.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentonatorDatasource {
	
	@Bean(name = "rentonatorDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.rentonator")
	public DataSource publicadorDataSource() {
		return DataSourceBuilder.create().build();
	}

}
