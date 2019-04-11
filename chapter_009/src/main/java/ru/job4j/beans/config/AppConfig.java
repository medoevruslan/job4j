package ru.job4j.beans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.job4j.beans.AbstractBean;
import ru.job4j.beans.AbstractInjectedBean;
import ru.job4j.beans.InjectedBean;
import ru.job4j.beans.MyBean;

@Configuration
public class AppConfig {

    @Bean
    public AbstractInjectedBean injectedBean() {
        return new InjectedBean();
    }

    @Bean
    public AbstractBean myBean() {
        return new MyBean(this.injectedBean());
    }
}
