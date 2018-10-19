package ru.job4j.meloman.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;
import ru.job4j.meloman.service.UserServiceStub;
import ru.job4j.meloman.service.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class, Utils.class})
public class UserCreateTest {

    private UserCreate create;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher dispatcher;
    private UserDAO validate;

    @Before
    public void init() {
        this.validate = new UserServiceStub();
        this.create = new UserCreate();
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
        PowerMockito.mockStatic(UserService.class);
        PowerMockito.when(UserService.getInstance()).thenReturn(validate);
    }

    @Test
    public void whenControllerForwardToUserViewPage() throws ServletException, IOException {
        when(this.req.getRequestDispatcher("WEB-INF/views/CreateView.jsp")).thenReturn(this.dispatcher);
        this.create.doGet(this.req, this.resp);
        verify(this.req.getRequestDispatcher("WEB-INF/views/CreateView.jsp"), times(1))
                .forward(this.req, this.resp);
    }

    @Test
    public void whenAddThreeElementsThenStoreThem() throws IOException, ServletException {
        Utils utils = mock(Utils.class);
        PowerMockito.mockStatic(Utils.class);
        when(Utils.getUtil()).thenReturn(utils);

        User first = new User(1, "firstUser");
        first.setLogin("first");
        User second = new User(2, "secondUser");
        second.setLogin("second");
        User third = new User(3, "thirdUser");
        third.setLogin("third");

        when(utils.convertJson(this.req, User.class)).thenReturn(first);
        this.create.doPost(this.req, this.resp);
        when(utils.convertJson(this.req, User.class)).thenReturn(second);
        this.create.doPost(this.req, this.resp);
        when(utils.convertJson(this.req, User.class)).thenReturn(third);
        this.create.doPost(this.req, this.resp);

        assertThat(this.validate.findAll().size(), is(3));
        assertThat(this.validate.findAll().get(1).getLogin(), is("second"));
    }

    @Test
    public void whenLoginIsExistThenMessage() throws IOException, ServletException {
        Utils utils = mock(Utils.class);
        PowerMockito.mockStatic(Utils.class);
        when(Utils.getUtil()).thenReturn(utils);

        User first = new User(1, "firstUser");
        first.setLogin("first");
        User second = new User(2, "secondUser");
        second.setLogin("first");

        when(utils.convertJson(this.req, User.class)).thenReturn(first);
        this.create.doPost(this.req, this.resp);
        when(utils.convertJson(this.req, User.class)).thenReturn(second);
        this.create.doPost(this.req, this.resp);

        assertThat(this.validate.findAll().size(), is(1));

    }
}