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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.assertThat;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

/*
 * Test does edit user.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateServletTest {

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
    public void whenUpdateUserNameAndLoginThenItChanged() throws IOException, ServletException {
        Mockito.when(this.req.getParameter("action")).thenReturn("add");
        Mockito.when(this.req.getParameter("login")).thenReturn("admin");
        Mockito.when(this.req.getParameter("name")).thenReturn("admin");
        Mockito.when(this.req.getParameter("email")).thenReturn("testMail");
        Mockito.when(req.getSession()).thenReturn(this.session);
        Mockito.when(this.session.getAttribute("user")).thenReturn(this.sessionUser);

        create.doPost(this.req, this.resp);

        List<User> users;
        users = this.validate.findAll();
        assertThat(users.get(0).getName(), is("admin"));
        assertThat(users.get(0).getEmail(), is("testMail"));

        Mockito.when(this.req.getContextPath()).thenReturn("http://localhost:8080/chapter_003");
        Mockito.when(this.req.getParameter("action")).thenReturn("update");
        Mockito.when(this.req.getParameter("id")).thenReturn("0");
        Mockito.when(this.req.getParameter("name")).thenReturn("changedN");
        Mockito.when(this.req.getParameter("login")).thenReturn("changedL");
        Mockito.when(this.req.getSession()).thenReturn(this.session);
        Mockito.when(this.session.getAttribute("user")).thenReturn(this.sessionUser);

        update.doPost(this.req, this.resp);

        verify(this.resp).sendRedirect("http://localhost:8080/chapter_003/user");
        assertThat(users.get(0).getName(), is("changedN"));
        assertThat(users.get(0).getLogin(), is("changedL"));
    }
}
