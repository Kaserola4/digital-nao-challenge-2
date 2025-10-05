package com.pikolinc.googlescholar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for REST client beans.
 * <p>
 * This configuration provides Spring-managed beans for HTTP communication,
 * specifically the {@link RestTemplate} used for making REST API calls to
 * external services such as SerpAPI.
 * </p>
 *
 * @author Pikolinc
 * @version 1.0
 * @see RestTemplate
 */
@Configuration
class RestTemplateConfig {

    /**
     * Creates and configures a {@link RestTemplate} bean for HTTP communication.
     * <p>
     * The RestTemplate is used throughout the application to make synchronous
     * HTTP requests to external APIs. This bean is singleton-scoped and shared
     * across the application.
     * </p>
     *
     * @return a configured RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}