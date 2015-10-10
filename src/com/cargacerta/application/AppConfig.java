package com.cargacerta.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan( basePackages = {"com.cargacerta.application",
								"com.cargacerta.controllers",
								"com.cargacerta.repositories"})
@EnableScheduling
class AppConfig {
}