package ru.job4j.carstore.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.carstore.ItemDispatcher;
import ru.job4j.carstore.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ItemDispatcher.class)
public class ItemCreateTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private RequestDispatcher requestDispatcher;
    private HttpSession session;
    private User user;
    private ItemCreate create;

    @Before
    public void setup() {
        this.session = mock(HttpSession.class);
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.requestDispatcher = mock(RequestDispatcher.class);
        this.user = new User("testLogin", "testPass", "testMail");
        this.create = new ItemCreate();

    }

    @Test
    public void testItemCreatePostMethod() throws Exception {
        when(this.req.getSession()).thenReturn(this.session);
        when(this.req.getSession().getAttribute("user")).thenReturn(this.user);
        when(this.req.getParameter("manufacturer")).thenReturn("manufacturer");
        when(this.req.getParameter("model")).thenReturn("model");
        when(this.req.getParameter("body_type")).thenReturn("body_type");
        when(this.req.getParameter("transmission")).thenReturn("transmission");
        when(this.req.getParameter("engine")).thenReturn("engine");
        when(this.req.getParameter("title")).thenReturn("title");
        when(this.req.getParameter("description")).thenReturn("description");
        when(this.req.getParameter("price")).thenReturn("2000");
        when(this.req.getContextPath()).thenReturn("chapter_008");

        ItemDispatcher dispatcher = mock(ItemDispatcher.class);
        PowerMockito.mockStatic(ItemDispatcher.class);
        when(ItemDispatcher.getInstance()).thenReturn(dispatcher);

        doNothing().when(dispatcher).createItem(this.req);

        this.create.doPost(this.req, this.resp);
        verify(this.resp, times(1)).sendRedirect("chapter_008/secure/profile");
    }

    @Test
    public void testItemCreateGetMethod() throws ServletException, IOException {
        when(this.req.getRequestDispatcher("/WEB-INF/views/NewItem.jsp")).thenReturn(this.requestDispatcher);
        this.create.doGet(this.req, this.resp);
        verify(this.req .getRequestDispatcher("/WEB-INF/views/NewItem.jsp"), times(1))
                .forward(this.req, this.resp);
    }
}
