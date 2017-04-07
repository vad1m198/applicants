package org.dynamo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DataSourceConfig {
	
    @Autowired
    private Environment env;
	
    @Bean(name = "dataSource")
    @Profile("default")
    public DataSource getProdDataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();    	
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("JDBC_DATABASE_URL"));
        dataSource.setUsername(env.getProperty("JDBC_DATABASE_USERNAME"));
        dataSource.setPassword(env.getProperty("JDBC_DATABASE_PASSWORD"));
        
        return dataSource;
    }
    
	@Bean
	@Profile("default")
	public NamedParameterJdbcTemplate getProdNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(getProdDataSource());
	}
    
    @Bean(name = "dataSource")
    @Profile("dev")
    public DataSource getDevDataSource() {
        return new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2)
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
            .addScript("db/h2db/schema.sql")
            .addScripts("db/h2db/insert-data.sql")
            .build();
    }
	
	@Bean
	@Profile("dev")
	public NamedParameterJdbcTemplate getDevNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(getDevDataSource());
	}

}
