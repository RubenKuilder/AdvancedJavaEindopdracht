package org.AdvancedJavaEindopdracht.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("org.AdvancedJavaEindopdracht.resource.repository")
public class DatabaseConfig
{
    @Value("${database.username}")
    private String databaseName;
    @Value("${database.password}")
    private String databasePassword;
    @Value("${database.url}")
    private String databaseUrl;

    @Bean(name = "dataSource")
    public DataSource datasource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(databaseUrl);
        ds.setUsername(databaseName);
        ds.setPassword(databasePassword);

        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);

        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPackagesToScan("org.AdvancedJavaEindopdracht.resource");
        em.setDataSource(datasource());
        em.setPersistenceUnitName("EmFactory");
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    private Properties additionalProperties()
    {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    @Bean
    public SpringLiquibase liquibase()
    {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(datasource());
        liquibase.setChangeLog("classpath:changelog_master.xml");
        return liquibase;
    }
}
