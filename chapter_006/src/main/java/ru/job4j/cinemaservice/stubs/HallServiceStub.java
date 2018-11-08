package ru.job4j.cinemaservice.stubs;

import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.HallDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Stub provides us to make the tests.
 */
public class HallServiceStub implements HallDAO {
    private final ArrayList<Hall> halls = new ArrayList<>();

    @Override
    public int add(Hall model) {
        this.halls.add(model);
        return model.getId();
    }

    @Override
    public boolean update(Hall model) {
        AtomicBoolean rst = new AtomicBoolean(false);
        IntStream.range(0, this.halls.size())
                .filter(idx -> model.getId() == halls.get(idx).getId())
                .findFirst().ifPresent(idx -> {
            rst.set(true);
            model.setId(this.halls.get(idx).getId());
            this.halls.set(idx, model);
        });
        return rst.get();
    }

    @Override
    public boolean remove(Hall model) {
        return this.halls.remove(model);
    }

    @Override
    public Optional<Hall> findById(int id) {
        return this.halls.stream()
                .filter(h -> h.getId() == id)
                .findFirst();
    }

    @Override
    public List<Hall> findAll() {
        return this.halls;
    }

    @Override
    public HashMap<Integer, ArrayList<Seat>> getSeatsByHall(Hall hall) {
        int seatId = 0;
        int rowLength = hall.getSeats() / hall.getRows();
        HashMap<Integer, ArrayList<Seat>> result = new HashMap<>();
        for (int row = 1; row < hall.getRows() + 1; row++) {
            ArrayList<Seat>  seats = new ArrayList<>();
            for (int seat = 1; seat < rowLength + 1; seat++) {
                String name = String.format("%s%s", row, seat);
                Seat s = new Seat(seatId++, name);
                s.setRowNum(row);
                s.setSeatNum(seat);
                seats.add(s);
            }
            result.put(row, seats);
        }
        return result;
    }
}
