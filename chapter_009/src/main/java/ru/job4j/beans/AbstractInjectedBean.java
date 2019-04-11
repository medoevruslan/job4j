package ru.job4j.beans;

public abstract class AbstractInjectedBean {

    public String beanMethod() {
        return String.format("%s method", this.getClass().getSimpleName());
    }
}
