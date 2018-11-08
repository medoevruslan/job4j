package ru.job4j.cinemaservice.stubs;

import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;
import ru.job4j.cinemaservice.persistent.SeatDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * Stub provides us to make the tests.
 */
public class SeatServiceStub implements SeatDAO {
    private final ArrayList<Seat> seats = new ArrayList<>();

    @Override
    public int add(Seat model) {
        this.seats.add(model);
        return model.getId();
    }

    @Override
    public boolean update(Seat model) {
        AtomicBoolean result = new AtomicBoolean(false);
        IntStream.range(0, this.seats.size())
                .filter(idx -> model.getId() == this.seats.get(idx).getId())
                .findFirst().ifPresent(idx -> {
            result.set(true);
            model.setId(this.seats.get(idx).getId());
            this.seats.set(idx, model);
        });
        return result.get();
    }

    @Override
    public boolean remove(Seat model) {
        return this.seats.remove(model);
    }

    @Override
    public Optional<Seat> findById(int id) {
        return this.seats.stream()
                .filter(s -> id == s.getId())
                .findFirst();
    }

    @Override
    public List<Seat> findAll() {
        return this.seats;
    }

    @Override
    public Optional<Account> findAccountBySeat(Seat seat) {
        return new AccountServiceStub().findAll().stream()
                .filter(acc -> seat.getId() == acc.getId())
                .findFirst();
    }

    @Override
    public boolean addRow(int seatsQuant, int price, Hall hall) {
        hall.setRows(hall.getRows() + 1);
        hall.setSeats(hall.getSeats() + seatsQuant);
        new HallServiceStub().getSeatsByHall(hall)
                .get(hall.getRows())
                .forEach(seat -> seat.setPrice(price));
        return true;
    }
}
