package com.file.notify;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@Slf4j
public class FileNotificationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(FileNotificationApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FileNotificationApplication.class);
	}

	@PostConstruct
	public void init(){
		log.info("APPLICATION STARTING....");
	}

}
