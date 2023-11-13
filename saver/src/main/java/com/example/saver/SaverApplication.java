package com.example.saver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import com.example.saver.configuration.properties.JwtProperties;
import com.example.saver.configuration.properties.StorageProperties;
import com.example.saver.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan(excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtProperties.class)})
public class SaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaverApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

}
