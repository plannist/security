package com.sec.prac.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages= {"com.sec.prac.mapper"}, sqlSessionFactoryRef="sqlSessionFactory", sqlSessionTemplateRef="sqlSessionTemplate")
//@MapperScan(basePackages= {"com.sec.prac.mapper"}, sqlSessionFactoryRef="dataSource", sqlSessionTemplateRef="sqlSessionFactoryBean")//VO있는package
@PropertySource("classpath:/db.properties")
public class DataSourceConfig {
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:mysql://localhost:3306/sys";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "root";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "pjs306";
	
	@Value("${jdbc.driver}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String URL;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;
	@Value("${datasource.maxtotal}")
	private int maxTotal;
	@Value("${datasource.maxidle}")
	private int maxIdle;
	
	
	
	@Resource
	private Environment enviroment;
	
	@Bean(name="dataSource")
	@Primary
    public DataSource dataSource() {
		System.out.println("@@@ db.properties driver: "+driverClassName+", URL: "+URL+", user: "+user+", passwoard: "+password);
        BasicDataSource dataSource = new BasicDataSource();
        
        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setMaxTotal(maxTotal);
        dataSource.setMaxIdle(maxIdle);
        
        return dataSource;

	}
	
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        /********* 가장 중요 **********
         * 1.)src/main/resources 에 mapper 폴더 만들고 그안에 xml 파일 생성
         * 2.)resource 아래 존재하는 mapper 폴더의 모든 xml 파일들이 
         * 	   관련 interface의  <mapper namespace="com.sec.prac.mapper.UserMapper">
         *    패키지와 class 풀네임 스페이스 값을 가져야한다.
         * 3.)UserMapper interface의 메서드 명은 mapping 되는 해당 xml 파일의 id값과 동일 해야한다.
         * 
         * ******************/
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml") );
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis.xml")); 
        return sessionFactory;
    }
    
    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
    	return new SqlSessionTemplate(sqlSessionFactory);
    }
    
   

}
