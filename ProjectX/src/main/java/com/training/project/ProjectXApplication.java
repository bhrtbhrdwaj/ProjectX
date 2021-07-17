package com.training.project;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@EntityScan("com.training.project.model")
@EnableJpaRepositories("com.training.project.repository")
@SpringBootApplication
public class ProjectXApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ProjectXApplication.class, args);
	}
	
	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplateBuilder().build();
	}
	
}
