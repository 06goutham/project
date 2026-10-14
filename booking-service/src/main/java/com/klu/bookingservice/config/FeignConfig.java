package com.klu.bookingservice.config;

import feign.RequestInterceptor;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor jwtForwardingInterceptor() {

        return template -> {

            var attributes =
                    RequestContextHolder.getRequestAttributes();

            if (attributes instanceof ServletRequestAttributes servletAttributes) {

                HttpServletRequest request =
                        servletAttributes.getRequest();

                String authorization =
                        request.getHeader("Authorization");

                if (authorization != null) {
                    template.header(
                            "Authorization",
                            authorization
                    );
                }
            }
        };
    }
}