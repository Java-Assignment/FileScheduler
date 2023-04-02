package com.abhi.filescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FileSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSchedulerApplication.class, args);
	}

}
