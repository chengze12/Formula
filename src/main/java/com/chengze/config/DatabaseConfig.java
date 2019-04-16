package com.chengze.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatabaseConfig {
    private String dburl = "jdbc:postgresql://localhost:5432/formula1";

    private String username = "admin";

    private String password = "password";

    private String driverClass = "org.postgresql.ds.PGSimpleDataSource";

    @Bean(name="dataSource")
    public DataSource getDataSource() {
        DataSource dataSource = createDataSource();
        return dataSource;
    }

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(dburl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
//        dataSource.setValidationQuery(databaseValidationQuery);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[] { "com.chengze.domain","com.chengze.repository" });
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
        props.put("hibernate.connection.charSet","UTF-8");
        props.put("hibernate.show_sql","true");
        factoryBean.setJpaProperties(props);

        return factoryBean;
    }
}
