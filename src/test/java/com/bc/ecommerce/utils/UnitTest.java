package com.bc.ecommerce.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategyImpl;

/**
 * Unitarian test class.
 */
public abstract class UnitTest {

    /* object mapper */
    protected ObjectMapper objectMapper;

    /* podam factory */
    protected PodamFactory factory;

    /**
     * init factory.
     */
    protected void initializeFactory() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger root = loggerContext.getLogger("uk.co.jemos.podam.api");
        root.setLevel(Level.ERROR);
        RandomDataProviderStrategyImpl randomDataProviderStrategy = new RandomDataProviderStrategyImpl();
        randomDataProviderStrategy.setDefaultNumberOfCollectionElements(1);
        randomDataProviderStrategy.setMaxDepth(1);
        factory = new PodamFactoryImpl();
        factory.setStrategy(randomDataProviderStrategy);

        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

}

