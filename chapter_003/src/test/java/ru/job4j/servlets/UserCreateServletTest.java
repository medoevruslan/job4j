package ru.job4j.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTest {

    private RequestDispatcher dispatcher;
    private HttpServletResponse resp;
    private UserCreateServlet create;
    private HttpServletRequest req;
    private HttpSession session;
    private Validate validate;
    private User sessionUser;

    @Before
    public void init() {
        this.validate = new ValidateStub();
        this.session = mock(HttpSession.class);
        this.create = new UserCreateServlet();
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.dispatcher = mock(RequestDispatcher.class);
        this.sessionUser = new User("admin", null, "admin", "admin");
        this.sessionUser.setRole(new Role("admin"));
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.when(ValidateService.getInstance()).thenReturn(validate);
    }

    @Test
    public void whenUserCreatePageExist() throws ServletException, IOException {
        when(this.req.getRequestDispatcher("/WEB-INF/views/CreateUser.jsp")).thenReturn(this.dispatcher);
        create.doGet(this.req, this.resp);
        verify(this.req, times(1)).getRequestDispatcher("/WEB-INF/views/CreateUser.jsp");
        verify(this.dispatcher).forward(this.req, this.resp);
    }

    @Test
    public void whenAddNewUserThenStoreIt() throws IOException, ServletException {
        Mockito.when(this.req.getContextPath()).thenReturn("http://localhost:8080/chapter_003");
        Mockito.when(this.req.getParameter("action")).thenReturn("add");
        Mockito.when(this.req.getParameter("login")).thenReturn("admin");
        Mockito.when(this.req.getSession()).thenReturn(session);
        Mockito.when(this.session.getAttribute("user")).thenReturn(this.sessionUser);
        this.create.doPost(this.req, this.resp);
        verify(this.resp).sendRedirect("http://localhost:8080/chapter_003/admin");
        assertThat(this.validate.findAll().iterator().next().getLogin(), is("admin"));
        this.validate.resetList();
    }

    @Test
    public void whenLoginIsAlreadyExistsThenMessage() throws IOException, ServletException {
        Mockito.when(this.req.getParameter("action")).thenReturn("add");
        Mockito.when(this.req.getParameter("login")).thenReturn("admin");
        Mockito.when(this.req.getSession()).thenReturn(session);
        Mockito.when(this.req.getRequestDispatcher("/WEB-INF/views/CreateUser.jsp")).thenReturn(this.dispatcher);
        Mockito.when(this.session.getAttribute("user")).thenReturn(this.sessionUser);
        this.create.doPost(this.req, this.resp);
        this.create.doPost(this.req, this.resp);
        verify(this.req).setAttribute("error", "That login is already exists");
    }
}