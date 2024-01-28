package com.bc.ecommerce.boot.spring.config;

import org.springframework.context.annotation.Configuration;

/**
 * Time zone configuration class. Default UTC zone.
 * In com.bc.ecommerce.boot.spring.config package.
 *
 * @author Álvaro Carmona
 * @since 28/01/2024
 */
@Configuration
public class TimeZoneConfig {

    static {
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
    }

}
