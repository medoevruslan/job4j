package ru.job4j.cinemaservice.controller;
import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.service.AccountService;
import ru.job4j.cinemaservice.service.SeatService;
import ru.job4j.cinemaservice.utils.ActionDispatcher;
import ru.job4j.cinemaservice.utils.StaticMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller that fetches elements from Hall, adds Account and links Account to Seat.
 */
public class HallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ActionDispatcher actionDispatcher = ActionDispatcher.getInstance();
        actionDispatcher.initialize();
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.write(actionDispatcher.fetchHallElems(req));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accId = req.getParameter("accId");
        String seatId = req.getParameter("seatId");
        if (accId == null) {
            PrintWriter writer = resp.getWriter();
            resp.setContentType("text/plain");
            Account account = StaticMapper.getInstance().convertJson(req, Account.class);
            String id = String.valueOf(AccountService.getInstance().add(account));
            writer.write(id);
            writer.flush();
        } else {
            Seat seat = SeatService.getInstance().findById(Integer.parseInt(seatId)).get();
            seat.setAccountId(Integer.parseInt(accId));
            SeatService.getInstance().update(seat);
        }
    }
}
