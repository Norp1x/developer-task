package com.search.service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Norpix on 03.11.2024.
 * Description: Configuration class with external endpoints mapping
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "api")
public class SearchServiceConfig {

    private String baseUrl;
    private String apiPath;
}
