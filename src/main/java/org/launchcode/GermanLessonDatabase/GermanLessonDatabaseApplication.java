package org.launchcode.GermanLessonDatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GermanLessonDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GermanLessonDatabaseApplication.class, args);
	}

@Bean
CommandLineRunner init(LessonsUpload lessonsUpload){
		return (args) -> {
			lessonsUpload.deleteAll();
			lessonsUpload.init();
		};
}

}
