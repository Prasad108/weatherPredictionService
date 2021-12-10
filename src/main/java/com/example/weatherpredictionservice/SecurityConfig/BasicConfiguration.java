package com.example.weatherpredictionservice.SecurityConfig;

import java.util.Arrays;
import lombok.var;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> {
      var cors = new CorsConfiguration();
      cors.setAllowedOrigins(Arrays.asList("*"));
      cors.setAllowedMethods(Arrays.asList("*"));
      cors.setAllowedHeaders(Arrays.asList("*"));
      return cors;
    }).and().csrf().disable()
          .httpBasic();
    }
}