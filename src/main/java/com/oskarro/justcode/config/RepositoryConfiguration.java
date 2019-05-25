package com.oskarro.justcode.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.oskarro.justcode.domains"})
@EnableJpaRepositories(basePackages = {"com.oskarro.justcode.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
