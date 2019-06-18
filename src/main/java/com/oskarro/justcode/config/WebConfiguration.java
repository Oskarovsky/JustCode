package com.oskarro.justcode.config;

import org.apache.catalina.servlets.WebdavServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebdavServlet());
        bean.addUrlMappings("/console/*");
        return bean;
    }

    //@Override
    //public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for CSS and JS
      //  registry.addResourceHandler("/resources/**")
        //        .addResourceLocations("/public", "classpath:/static/")
          //      .setCachePeriod(31556926);
    //}
}
