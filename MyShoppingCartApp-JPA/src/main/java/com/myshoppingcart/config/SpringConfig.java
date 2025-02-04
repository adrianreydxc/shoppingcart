package com.myshoppingcart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({ReposConfig.class, ServicesConfig.class})
@PropertySource("classpath:application.properties")
public class SpringConfig {
}
