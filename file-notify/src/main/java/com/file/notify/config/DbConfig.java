package com.file.notify.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.file.notify.repository","com.file.notify.model"},
        entityManagerFactoryRef= "notifyEntityManagerFactory"
        )
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@Slf4j
public class DbConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        HikariDataSource dataSource = new HikariDataSource(hikariConfig());
        log.info("DATABASE CONNECTION STATUS............ Connected");
        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean notifyEntityManagerFactory() throws SQLException {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.file.notify.model","com.file.notify.repository");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        em.setPersistenceUnitName("testLoad");
        return em;
    }

    /*@Bean transactionManagerRef= "notifyTransactionManager"
    public EntityManagerFactory testEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) throws SQLException {
        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(dataSource)
                .packages("com.file.notify.model")  // Specify the package where your @Entity classes are located
                .persistenceUnit("testLoad")  // Optional: persistence unit name
                .properties(hibernateProperties())  // Hibernate specific properties
                .build();
        return factoryBean.getObject();
    }*/


    public Properties hibernateProperties(){
        log.info("Hibernate Properties...");
        Properties properties = new Properties();
        //properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.default_schema", "Notify");
        properties.put("hikari.autoCommit","true");
        return properties;
    }

    public static HikariConfig hikariConfig(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/Notify");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername("root");
        config.setPassword("baku");

        // Optional: Configure additional connection pool settings
        config.setMaximumPoolSize(10);  // Set the max pool size
        config.setMinimumIdle(5);       // Set the minimum idle connections
        config.setConnectionTimeout(30000);  // Set the connection timeout in milliseconds
        config.setIdleTimeout(600000);   // Set the idle timeout in milliseconds
        config.setMaxLifetime(1800000);
        config.setAutoCommit(true);
        return config;
    }

   // Define the TransactionManager Bean for transaction management
   @Bean
   public PlatformTransactionManager transactionManager() throws SQLException {
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(notifyEntityManagerFactory().getObject());

       return transactionManager;
   }
}
