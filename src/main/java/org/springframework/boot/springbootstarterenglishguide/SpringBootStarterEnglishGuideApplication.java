package org.springframework.boot.springbootstarterenglishguide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("org.springframework.boot.springbootstarterenglishguide.repository")
@EntityScan("org.springframework.boot.springbootstarterenglishguide.entity")
@SpringBootApplication
public class SpringBootStarterEnglishGuideApplication	{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterEnglishGuideApplication.class, args);
	}

}
