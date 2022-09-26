package fr.formation.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement // Activer les annotations @Transactional
public class JpaConfig {
	// DATASOURCE
	// @Bean
	public DataSource dataSourceMysql() {
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/eshop");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setMaximumPoolSize(10);

		return dataSource;
	}

	@Bean
	public DataSource dataSourceMssql() {
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setJdbcUrl("jdbc:sqlserver://localhost:1433;databaseName=eshop;trustServerCertificate=true");
		dataSource.setUsername("sa");
		dataSource.setPassword("Not24Get");
		dataSource.setMaximumPoolSize(10);

		return dataSource;
	}

	// Entity Manager Factory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		Properties hibernateProps = new Properties();

		hibernateProps.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProps.setProperty("hibernate.show_sql", "true");

		emf.setDataSource(dataSource);
		emf.setPackagesToScan("fr.formation.model");
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setJpaProperties(hibernateProps);

		return emf;
	}

	// Gestionnaire de transaction
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
