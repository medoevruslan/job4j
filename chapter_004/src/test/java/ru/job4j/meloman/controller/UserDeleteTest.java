package ru.job4j.meloman.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;
import ru.job4j.meloman.service.UserServiceStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserDeleteTest {

    private HttpServletResponse resp;
    private HttpServletRequest req;
    private UserDelete delete;
    private UserDAO validate;

    @Test
    public void wheneDeleteUserThenItDeleted() throws ServletException, IOException {
        this.delete = new UserDelete();
        this.validate = new UserServiceStub();
        PowerMockito.mockStatic(UserService.class);
        PowerMockito.when(UserService.getInstance()).thenReturn(this.validate);
        this.resp = mock(HttpServletResponse.class);
        this.req = mock(HttpServletRequest.class);

        User first = new User(0, "testName");
        User second = new User(1, "testName");
        first.setLogin("firstLogin");
        second.setLogin("secondLogin");

        this.validate.add(first);
        this.validate.add(second);

        when(this.req.getParameter("id")).thenReturn("0");

        assertThat(this.validate.findAll().size(), is(2));

        this.delete.doGet(this.req, this.resp);
        assertThat(this.validate.findAll().size(), is(1));

        when(this.req.getParameter("id")).thenReturn("1");

        this.delete.doGet(this.req, this.resp);
        assertThat(this.validate.findAll().size(), is(0));
    }
}