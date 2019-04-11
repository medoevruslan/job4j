package ru.job4j.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.model.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    private ApplicationContext context;

    public void setUp(String format) {
        switch (format) {
            case "xml" :
                this.context = new ClassPathXmlApplicationContext("storage-spring-context.xml");
                break;
            case "annotation" :
                this.context = new AnnotationConfigApplicationContext(AppConfig.class);
                break;
            default:
                throw new RuntimeException("There is no such format");
        }
    }

    @Test
    public void whenXmlStoreNewUserThenItStored() {
        this.setUp("xml");
        UserStorage storage = (UserStorage) this.context.getBean("userMemStorage");
        storage.add(new User("testName", "testSurname"));
        assertThat(storage.findAll().size(), is(1));
        assertThat(storage.findAll().get(0).getName(), is("testName"));
        storage.add(new User("secondTestName", "secondTestSurname"));
        assertThat(storage.findAll().size(), is(2));
        assertThat(storage.findAll().get(1).getSurname(), is("secondTestSurname"));
    }

    @Test
    public void whenXmlUpdateUserThenItChanged() {
        this.setUp("xml");
        UserStorage storage = (UserStorage) this.context.getBean("userMemStorage");
        User user = new User("testName", "testSurname");
        storage.add(user);
        user.setName("anotherTestName");
        storage.update(user);
        assertThat(storage.findAll().get(0).getName(), is("anotherTestName"));
    }

    @Test
    public void whenXmlStoreUserToDataBaseThanItStored() {
        this.setUp("xml");
        UserStorage storage = (UserStorage) this.context.getBean("userJdbcStorage");
        storage.add(new User("testName", "testSurname"));
        assertThat(storage.findAll().get(0).getName(), is("testName"));
        storage.add(new User("secondTestName", "secondTestSurname"));
        assertThat(storage.findAll().get(1).getName(), is("secondTestName"));

    }

    @Test
    public void whenAnnotationStoreNewUserThenItStored() {
        this.setUp("annotation");
        UserStorage storage = this.context.getBean(UserStorage.UserMemoryStorage.class);
        storage.add(new User("testName", "testSurname"));
        assertThat(storage.findAll().size(), is(1));
        assertThat(storage.findAll().get(0).getName(), is("testName"));
        storage.add(new User("secondTestName", "secondTestSurname"));
        assertThat(storage.findAll().size(), is(2));
        assertThat(storage.findAll().get(1).getSurname(), is("secondTestSurname"));
    }

    @Test
    public void whenAnnotationlUpdateUserThenItChanged() {
        this.setUp("annotation");
        UserStorage storage = this.context.getBean(UserStorage.UserMemoryStorage.class);
        User user = new User("testName", "testSurname");
        storage.add(user);
        user.setName("anotherTestName");
        storage.update(user);
        assertThat(storage.findAll().get(0).getName(), is("anotherTestName"));
    }

    @Test
    public void whenAnnotationStoreUserToDataBaseThanItStored() {
        this.setUp("annotation");
        UserStorage storage = (UserStorage) this.context.getBean("userJdbcStorage");
        storage.add(new User("testName", "testSurname"));
        assertThat(storage.findAll().get(0).getName(), is("testName"));
        storage.add(new User("secondTestName", "secondTestSurname"));
        assertThat(storage.findAll().get(1).getName(), is("secondTestName"));

    }
}