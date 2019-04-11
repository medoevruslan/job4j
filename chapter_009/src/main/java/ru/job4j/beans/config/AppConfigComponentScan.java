package ru.job4j.beans.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ru.job4j.beans")
public class AppConfigComponentScan {
}
