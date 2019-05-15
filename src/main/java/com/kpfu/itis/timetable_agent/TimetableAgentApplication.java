package com.kpfu.itis.timetable_agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.kpfu.itis.timetable_agent")
@EntityScan(basePackages = {"com.kpfu.itis.timetable_agent.models"})
@EnableJpaRepositories(basePackages = {"com.kpfu.itis.timetable_agent.repositories"})
@SpringBootApplication
public class TimetableAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableAgentApplication.class, args);
	}

}
