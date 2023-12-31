package com.ssafy.la.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DatabaseConfig {

	@Bean(name = "loginAdventureDataSource")
	@ConfigurationProperties(prefix = "spring.ladb.datasource")
	public DataSource loginAdventureDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "loginAdventureSqlSessionFactory")
	public SqlSessionFactory loginAdventureSqlSessionFactory(
			@Qualifier("loginAdventureDataSource") DataSource loginAdventureDataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(loginAdventureDataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:/mybatis/mappers/loginadventure/*/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "loginAdventureSqlSessionTemplate")
	public SqlSessionTemplate loginAdventureSqlSessionTemplate(SqlSessionFactory securitySqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(securitySqlSessionFactory);
	}

	@Bean(name = "loginAdventuretransactionManager")
	@Primary
	public PlatformTransactionManager loginAdventureTransactionManager(@Qualifier("loginAdventureDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "securityDataSource")
	@ConfigurationProperties(prefix = "spring.secdb.datasource")
	public DataSource securityDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "securitySqlSessionFactory")
	public SqlSessionFactory securitySqlSessionFactory(@Qualifier("securityDataSource") DataSource securityDataSource)
			throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(securityDataSource);
		sqlSessionFactoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mappers/security/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "securitySqlSessionTemplate")
	public SqlSessionTemplate securitySqlSessionTemplate(SqlSessionFactory securitySqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(securitySqlSessionFactory);
	}

	@Bean(name = "securityTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("securityDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}
