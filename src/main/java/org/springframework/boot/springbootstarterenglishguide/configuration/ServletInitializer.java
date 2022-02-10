package org.springframework.boot.springbootstarterenglishguide.configuration;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.springbootstarterenglishguide.SpringBootStarterEnglishGuideApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootStarterEnglishGuideApplication.class);
	}

}
