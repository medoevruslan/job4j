package ru.job4j.storage;


import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.job4j.storage")
public class AppConfig {

    @Value("classpath:ru/job4j/storage/db/sql/create-db.sql")
    private Resource createDb;

    @Bean
    public DataSource getDataSrc() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass("org.hsqldb.jdbcDriver");
        dataSource.setJdbcUrl("jdbc:hsqldb:mem:userstore;sql.enforce.size=false");
        dataSource.setUser("SA");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public DataSourceInitializer createDataSrc() {
        DataSourceInitializer srcInitializer = new DataSourceInitializer();
        srcInitializer.setDataSource(this.getDataSrc());
        srcInitializer.setDatabasePopulator(this.databasePopulator());
        return srcInitializer;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(this.createDb);
        return populator;
    }
}
