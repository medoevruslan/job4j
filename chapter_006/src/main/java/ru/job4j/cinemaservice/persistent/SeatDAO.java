package ru.job4j.cinemaservice.persistent;

import ru.job4j.cinemaservice.model.Seat;

/**
 * DAO to deal with Seat entity.
 */
public interface SeatDAO extends DAO<Seat>, SeatRepository {
}
