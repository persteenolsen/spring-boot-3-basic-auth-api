package com.spring3.oauth.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class OauthBasicApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OauthBasicApplication.class, args);
    }

	// Ready for builing a war file used for Tomcat at Azure
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OauthBasicApplication.class);
	}
}
