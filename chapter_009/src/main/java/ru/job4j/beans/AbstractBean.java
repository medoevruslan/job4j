package ru.job4j.beans;

public abstract class AbstractBean {

    private final AbstractInjectedBean injectedBean;

    public AbstractBean(AbstractInjectedBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    public String beanMethod() {
        return String.format("%s method", this.getClass().getSimpleName());
    }

    public String injectedMethod() {
        return this.injectedBean.beanMethod();
    }
}
