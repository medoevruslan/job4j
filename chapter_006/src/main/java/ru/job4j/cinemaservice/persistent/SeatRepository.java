package ru.job4j.cinemaservice.persistent;

import ru.job4j.cinemaservice.model.Account;
import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;

import java.util.Optional;

public interface SeatRepository {

    Optional<Account> findAccountBySeat(Seat seat);
    boolean addRow(int seatsQuant, int price, Hall hall);

}
