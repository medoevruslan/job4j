package ru.job4j.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.beans.config.AppConfig;
import ru.job4j.beans.config.AppConfigComponentScan;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MyBeanTest {

    @Test
    public void whenCreateSimpleBeanAppConfigThenItCreated() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AbstractInjectedBean bean = context.getBean(InjectedBean.class);
        assertNotNull(bean);
        assertThat(bean.beanMethod(), is("InjectedBean method"));
    }

    @Test
    public void whenCreateBeanWithInjectionAppConfigThenItCreated() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AbstractBean bean = context.getBean(MyBean.class);
        this.assertInjection(bean);
    }

    @Test
    public void whenCreateBeanWithInjectionAppConfigAndAutoScanThenItCreated() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigComponentScan.class);
        AbstractBean bean = context.getBean(MyBean.class);
        this.assertInjection(bean);
    }

    @Test
    public void whenCreateSimpleBeanThenItCreated() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        AbstractInjectedBean bean = context.getBean(InjectedBean.class);
        assertNotNull(bean);
        assertThat(bean.beanMethod(), is("InjectedBean method"));
    }

    @Test
    public void whenCreateBeanWithInjectionThenItCreated() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        AbstractBean bean = context.getBean(MyBean.class);
        assertNotNull(bean);
        this.assertInjection(bean);
    }

    @Test
    public void whenCreateBeanWithInjectionAndAutoScanThenItCreated() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-auto-scan.xml");
        AbstractBean bean = context.getBean(MyBean.class);
        this.assertInjection(bean);
    }

    private void assertInjection(AbstractBean bean) {
        assertNotNull(bean);
        assertThat(bean.beanMethod(), is("MyBean method"));
        assertThat(bean.injectedMethod(), is("InjectedBean method"));
    }
}