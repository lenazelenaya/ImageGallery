package com.agileengine.gallery.configuration;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HTTPClosableClient {
    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }
}
