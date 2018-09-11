package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Test does delete user using UserUpdateServlet.class
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserDeleteServletTest {

    private HttpServletResponse resp;
    private UserCreateServlet create;
    private UserUpdateServlet update;
    private HttpServletRequest req;
    private HttpSession session;
    private Validate validate;
    private User sessionUser;

    @Before
    public void init()  {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.session = mock(HttpSession.class);
        this.sessionUser = new User("user","userMail", "user","user");
        this.sessionUser.setRole(new Role("user"));
        this.create = new UserCreateServlet();
        this.update = new UserUpdateServlet();
        this.validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.when(ValidateService.getInstance()).thenReturn(validate);
    }

    @Test
    public void whenDeletOneOfTwoeUsersThenOneLeft() throws IOException, ServletException {
        Mockito.when(this.req.getParameter("action")).thenReturn("add");
        Mockito.when(this.req.getParameter("login")).thenReturn("admin");
        Mockito.when(this.req.getParameter("name")).thenReturn("admin");
        Mockito.when(this.req.getParameter("email")).thenReturn("testMail");
        Mockito.when(req.getSession()).thenReturn(this.session);
        Mockito.when(this.session.getAttribute("user")).thenReturn(this.sessionUser);

        this.create.doPost(this.req, this.resp);

        Mockito.when(this.req.getParameter("action")).thenReturn("add");
        Mockito.when(this.req.getParameter("login")).thenReturn("user");
        Mockito.when(this.req.getParameter("name")).thenReturn("user");
        Mockito.when(this.session.getAttribute("user")).thenReturn(this.sessionUser);
        Mockito.when(req.getSession()).thenReturn(this.session);

        this.create.doPost(this.req, this.resp);

        List<User> users;
        users = this.validate.findAll();
        assertThat(users.size(), is (2));

        Mockito.when(req.getParameter("action")).thenReturn("delete");
        Mockito.when(req.getParameter("id")).thenReturn("0");

        this.update.doPost(this.req, this.resp);

        assertThat(users.size(), is(1));
        assertThat(users.get(0).getName(), is("user"));
    }
}
