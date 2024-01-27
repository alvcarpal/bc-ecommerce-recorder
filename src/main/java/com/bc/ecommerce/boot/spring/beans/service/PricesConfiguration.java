package com.bc.ecommerce.boot.spring.beans.service;

import com.bc.ecommerce.application.usescases.PricesUseCase;
import com.bc.ecommerce.domain.port.in.PricesService;
import com.bc.ecommerce.domain.port.out.PricesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Prices service configuration class.
 * In com.bc.ecommerce.boot.spring.beans.service package.
 *
 * @author Álvaro Carmona
 * @since 27/01/2024
 */
@Configuration
public class PricesConfiguration {

    /**
     * Prices service bean.
     *
     * @param repository Prices repository out port.
     * @return The created bean.
     */
    @Bean
    public PricesService pricesService(PricesRepository repository) {
        return new PricesUseCase(repository);
    }

}
