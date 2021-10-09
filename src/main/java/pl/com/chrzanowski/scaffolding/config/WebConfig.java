package pl.com.chrzanowski.scaffolding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private ApplicationConfig applicationConfig;

    public WebConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        "/img/**",
                        "/css/**",
                        "/libs/**",
                        "/fonts/**",
                        "/js/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/libs/",
                        "classpath:/static/fonts/",
                        "classpath:/static/js/");


    }

}
