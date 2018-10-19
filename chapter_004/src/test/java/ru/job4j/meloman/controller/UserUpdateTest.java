package ru.job4j.meloman.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.entity.Address;
import ru.job4j.meloman.entity.Role;
import ru.job4j.meloman.entity.User;
import ru.job4j.meloman.service.UserService;
import ru.job4j.meloman.service.UserServiceStub;
import ru.job4j.meloman.service.Utils;
import ru.job4j.meloman.service.ViewSelector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class, Utils.class, ViewSelector.class})
public class UserUpdateTest {

    private RequestDispatcher dispatcher;
    private HttpServletResponse resp;
    private HttpServletRequest req;
    private UserCreate create;
    private UserUpdate update;
    private UserDAO validate;
    private User user;

    @Before
    public void setUp() {
        this.dispatcher = mock(RequestDispatcher.class);
        this.resp = mock(HttpServletResponse.class);
        this.req = mock(HttpServletRequest.class);
        this.validate = new UserServiceStub();
        this.create = new UserCreate();
        this.update = new UserUpdate();
        PowerMockito.mockStatic(UserService.class);
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(UserService.getInstance()).thenReturn(this.validate);
        Role role = new Role(1, "user");
        Address address = new Address(1, "address");
        this.user = new User(1, "testName");
        this.user.setLogin("testLogin");
        this.user.setRole(role);
        this.user.setAddress(address);
    }

    @Test
    public void whenUpdateLoginThenItChanged() throws ServletException, IOException {
        Utils utils = mock(Utils.class);
        PowerMockito.mockStatic(Utils.class);
        when(Utils.getUtil()).thenReturn(utils);

        when(utils.convertJson(this.req, User.class)).thenReturn(this.user);
        this.create.doPost(this.req, this.resp);

        assertThat(this.validate.findAll().get(0).getName(), is("testName"));
        assertThat(this.validate.findAll().get(0).getLogin(), is("testLogin"));

        this.user.setName("updatedName");
        this.user.setLogin("updatedLogin");

        when(utils.convertJson(this.req, User.class)).thenReturn(this.user);
        this.update.doPost(this.req, this.resp);

        assertThat(this.validate.findAll().get(0).getName(), is("updatedName"));
        assertThat(this.validate.findAll().get(0).getLogin(), is("updatedLogin"));
    }

    @Test
    public void whenSessionUserRoleIsUserThenForwardToUserEditView() throws IOException, ServletException {
        HttpSession session = mock(HttpSession.class);
        when(this.req.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(this.user);

        this.validate.add(this.user);

        when(this.req.getParameter("id")).thenReturn("0");
        when(this.req.getRequestDispatcher("WEB-INF/views/UserEditView.jsp")).thenReturn(this.dispatcher);

        this.update.doGet(this.req, this.resp);

        verify(this.req.getRequestDispatcher("WEB-INF/views/UserEditView.jsp"), times(1))
                .forward(this.req, this.resp);
        }
}