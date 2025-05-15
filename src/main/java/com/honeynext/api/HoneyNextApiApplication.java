package com.honeynext.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class to bootstrap the HoneyNext API.
 * Enables auto-configuration and component scanning.
 */
@SpringBootApplication
public class HoneyNextApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HoneyNextApiApplication.class, args);
    }
}