package com.gamaset.rentonator.infra.config.datasource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.gamaset.rentonator.repository"},
        entityManagerFactoryRef = "rentonatorEntityManagerFactory",
        transactionManagerRef = "rentonatorTransactionManager")
@EnableTransactionManagement
public class RentonatorDatasource {

	@Primary
    @Bean(name = "rentonatorDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

	@Primary
    @Bean(name="sqlServerJpaPropertiesRentonator")
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties mySqlJpaProperties() {
        return new JpaProperties();
    }

	@Primary
    @Bean(name = "rentonatorEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("rentonatorDataSource") DataSource dataSource) {
        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(serverJpaVendorAdapter(),
                mySqlJpaProperties().getProperties(), null);
        return builder
                .dataSource(dataSource)
                .packages("com.gamaset.rentonator.repository.entity")
                .persistenceUnit("rentonator")
                .build();
    }

	@Primary
    @Bean(name = "rentonatorTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("rentonatorEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private JpaVendorAdapter serverJpaVendorAdapter() {
        JpaProperties jpaProperties = mySqlJpaProperties();
        HibernateJpaVendorAdapter hjpva = new HibernateJpaVendorAdapter();
        hjpva.setDatabase(jpaProperties.getDatabase());
        hjpva.setShowSql(jpaProperties.isShowSql());
        hjpva.getJpaPropertyMap().putAll(jpaProperties.getProperties());
        hjpva.setGenerateDdl(jpaProperties.isGenerateDdl());
        return hjpva;
    }
}