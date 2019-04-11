package ru.job4j.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBean extends AbstractBean {

    @Autowired
    public MyBean(AbstractInjectedBean injectedBean) {
        super(injectedBean);
    }
}
