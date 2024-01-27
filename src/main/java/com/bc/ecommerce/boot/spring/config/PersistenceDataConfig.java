package com.bc.ecommerce.boot.spring.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.bc.ecommerce.infrastructure.db.springdata.repository")
@ConfigurationProperties("spring.datasource")
@NoArgsConstructor
@Getter
@Setter
@EnableJpaAuditing
@EntityScan(basePackages = "com.bc.ecommerce.infrastructure.db.springdata.model")
public class PersistenceDataConfig {

  private String url;

  private String username;

  private String driverClassName;

  /**
   * dataSource.
   *
   * @return a {@link DataSource} object.
   * @throws SQLException if any.
   * @throws ClassNotFoundException if any.
   */
  @SuppressWarnings("unchecked")
  @Bean
  @Primary
  public DataSource dataSource() throws SQLException, ClassNotFoundException {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriver(DriverManager.getDriver(url));
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setDriverClass((Class<? extends Driver>) Class.forName(driverClassName));
    Properties connectionProperties = new Properties();
    dataSource.setConnectionProperties(connectionProperties);
    return dataSource;
  }

}
