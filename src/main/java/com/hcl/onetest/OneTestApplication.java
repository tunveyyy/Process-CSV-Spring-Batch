package com.hcl.onetest;

import com.hcl.onetest.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class OneTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneTestApplication.class, args);
	}

}
