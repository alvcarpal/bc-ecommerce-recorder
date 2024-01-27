package com.bc.ecommerce.boot.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EcommerceRecorderSpringBootService class.
 * Main method.
 * In com.bc.ecommerce.boot.spring.config package.
 *
 * @author Álvaro Carmona
 * @since 26/01/2024
 */
@SpringBootApplication(scanBasePackages = {
        "com.bc.ecommerce.infrastructure",
        "com.bc.ecommerce.application",
        "com.bc.ecommerce.boot"
})
public class EcommerceRecorderSpringBootService {

    /**
     * Main method.
     *
     * @param args a {@link String} object.
     */
    public static void main(String[] args) {
        SpringApplication.run(EcommerceRecorderSpringBootService.class, args);
    }

}

