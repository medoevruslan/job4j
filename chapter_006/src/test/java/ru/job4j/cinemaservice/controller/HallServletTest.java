package ru.job4j.cinemaservice.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.cinemaservice.utils.StaticMapper;
import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.AccountDAO;
import ru.job4j.cinemaservice.persistent.HallDAO;
import ru.job4j.cinemaservice.persistent.SeatDAO;
import ru.job4j.cinemaservice.service.AccountService;
import ru.job4j.cinemaservice.service.HallService;
import ru.job4j.cinemaservice.service.SeatService;
import ru.job4j.cinemaservice.stubs.AccountServiceStub;
import ru.job4j.cinemaservice.stubs.HallServiceStub;
import ru.job4j.cinemaservice.stubs.SeatServiceStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AccountService.class, HallService.class, SeatService.class, StaticMapper.class})
public class HallServletTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HallServlet hallServlet;
    private AccountDAO accountDAO;
    private SeatDAO seatDAO;
    private HallDAO hallDAO;

    @Before
    public void setUp() {
        this.resp = Mockito.mock(HttpServletResponse.class);
        this.req = Mockito.mock(HttpServletRequest.class);
        this.accountDAO = new AccountServiceStub();
        this.seatDAO = new SeatServiceStub();
        this.hallDAO = new HallServiceStub();
        this.hallServlet = new HallServlet();
        PowerMockito.mockStatic(AccountService.class);
        PowerMockito.mockStatic(HallService.class);
        PowerMockito.mockStatic(SeatService.class);
        PowerMockito.when(AccountService.getInstance()).thenReturn(this.accountDAO);
        PowerMockito.when(SeatService.getInstance()).thenReturn(this.seatDAO);
        PowerMockito.when(HallService.getInstance()).thenReturn(this.hallDAO);
    }

    @Test
    public void whenFetchHollRowsLengthThen3() throws IOException, ServletException {
        Hall hall = new Hall(1, "hallA");
        hall.setSeats(9);
        hall.setRows(3);
        this.hallDAO.add(hall);

        PrintWriter writer = Mockito.mock(PrintWriter.class);
        when(this.resp.getWriter()).thenReturn(writer);

        when(req.getParameter("hallName")).thenReturn("hallA");
        when(req.getParameter("request")).thenReturn("getRowLength");

        this.hallServlet.doGet(this.req, this.resp);

        verify(writer).write("3");
    }

    @Test
    public void whenFetchHollRows() throws IOException, ServletException {
        Hall hall = new Hall(1, "hallA");
        hall.setSeats(9);
        hall.setRows(3);
        this.hallDAO.add(hall);

        PrintWriter writer = mock(PrintWriter.class);
        when(this.resp.getWriter()).thenReturn(writer);

        when(req.getParameter("hallName")).thenReturn("hallA");
        when(req.getParameter("request")).thenReturn("getRows");

        this.hallServlet.doGet(this.req, this.resp);
    }

    @Test
    public void whenAddNewAccountThenItStored() throws IOException, ServletException {
        StaticMapper mapper = mock(StaticMapper.class);
        PowerMockito.mockStatic(StaticMapper.class);
        when(StaticMapper.getInstance()).thenReturn(mapper);

        PrintWriter writer = mock(PrintWriter.class);
        when(this.resp.getWriter()).thenReturn(writer);

        Account firstAcc = new Account(1, "firstTestAcc");

        when(mapper.convertJson(this.req, Account.class)).thenReturn(firstAcc);

        this.hallServlet.doPost(this.req, this.resp);

        assertThat(this.accountDAO.findAll().size(), is(1));
        assertThat(this.accountDAO.findAll().get(0).getName(), is("firstTestAcc"));

        Account secondAcc = new Account(1, "secondTestAcc");

        when(mapper.convertJson(this.req, Account.class)).thenReturn(secondAcc);

        this.hallServlet.doPost(this.req, this.resp);

        assertThat(this.accountDAO.findAll().size(), is(2));
        assertThat(this.accountDAO.findAll().get(1).getName(), is("secondTestAcc"));
    }

    @Test
    public void whenAccountLinkToSeatThenSeatHasCurrentAccountId() throws ServletException, IOException {
        when(this.req.getParameter("accId")).thenReturn("1");
        when(this.req.getParameter("seatId")).thenReturn("1");

        Seat seat = new Seat(1, "testSeat");
        this.seatDAO.add(seat);

        assertThat(seat.getAccountId(), is(0));

        this.hallServlet.doPost(this.req, this.resp);

        assertThat(seat.getAccountId(), is(1));
    }
}