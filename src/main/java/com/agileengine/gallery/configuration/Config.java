package com.agileengine.gallery.configuration;

import com.agileengine.gallery.helper.GalleryHelper;
import com.agileengine.gallery.model.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class Config {
    @Bean
    public Response getToken(){
        return GalleryHelper.auth();
    }
}
