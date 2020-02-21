/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ozar.gokhan.training.springboot.countrycityperson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 * @author Ozar <gosocial2@ozar.net>
 */
@Configuration
public class WebMvcConfig  extends WebMvcConfigurationSupport {
    
    private static final String RESOURCES_LOCATION = "/static/";
    private static final String RESOURCES_HANDLER = RESOURCES_LOCATION + "**";
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(RESOURCES_HANDLER).addResourceLocations("classpath:"+RESOURCES_LOCATION);
        /*
        registry.addResourceHandler(
                "/static/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/static/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
        */
    }
}
