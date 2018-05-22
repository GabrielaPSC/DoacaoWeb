package br.com.doacao.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
            SpringApplication.run(WebappApplication.class, args);
    }
        
    @Bean
    public FilterRegistrationBean corsFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CrossOriginRequestFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}
