package edu.utdallas.sxb170035.library_app.data.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class MainApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainApplication.class, args);
	}

}
