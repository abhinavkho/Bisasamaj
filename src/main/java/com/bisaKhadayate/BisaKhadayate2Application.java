package com.bisaKhadayate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BisaKhadayate2Application  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BisaKhadayate2Application.class, args);
	}

	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
	         return app.sources(BisaKhadayate2Application.class);
	     }
	
}
