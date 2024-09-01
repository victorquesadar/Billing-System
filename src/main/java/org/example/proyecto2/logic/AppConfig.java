package org.example.proyecto2.logic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
    @Value("${user.dir}")
    private String projectRootDir;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                String staticLocation = "file:" + projectRootDir + "\\";
                System.out.println(staticLocation);
                registry.addResourceHandler("/static/**", "/**").addResourceLocations("classpath:/static/", staticLocation);
            }
        };
    }
}