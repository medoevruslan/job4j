package ru.job4j.cinemaservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.HallDAO;
import ru.job4j.cinemaservice.service.HallService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Class provides us with methods to fetch Hall elements.
 */
public class ActionDispatcher {
    private final static Logger LOG = Logger.getLogger(ActionDispatcher.class);
    private static final ActionDispatcher INSTANCE = new ActionDispatcher();
    private final Map<String, Function<HttpServletRequest, String>> actions = new ConcurrentHashMap<>();

    private ActionDispatcher() { }

    public static ActionDispatcher getInstance() {
        return INSTANCE;
    }

    private Function<HttpServletRequest, String> getRowsLength() {
        return req -> {
            String hallName = req.getParameter("hallName");
            HallDAO service = HallService.getInstance();
            List<Hall> halls = service.findAll();
            HashMap<Integer, ArrayList<Seat>> rows = service
                    .getSeatsByHall(halls.stream()
                            .filter(hall -> hall.getName().equals(hallName)).findFirst().get());
            return String.valueOf(rows.values().size());
        };
    }

    private Function<HttpServletRequest, String> getRows() {
        return req -> {
            String result = "";
            String hallName = req.getParameter("hallName");
            HallDAO service = HallService.getInstance();
            List<Hall> halls = service.findAll();
            HashMap<Integer, ArrayList<Seat>> rows = service
                    .getSeatsByHall(halls.stream()
                            .filter(hall -> hall.getName().equals(hallName)).findFirst().get());
            try {
                result = StaticMapper.MAPPER.writeValueAsString(rows);
            } catch (JsonProcessingException e) {
               LOG.error("Can't convert hall rows to string", e);
            }
            return result;
        };
    }

    public void load(String action, Function<HttpServletRequest, String> handler) {
        this.actions.put(action, handler);
    }

    public void initialize() {
        this.load("getRowLength", this.getRowsLength());
        this.load("getRows", this.getRows());
    }

    public String fetchHallElems(HttpServletRequest req) {
        return this.actions.get(req.getParameter("request")).apply(req);
    }
}
