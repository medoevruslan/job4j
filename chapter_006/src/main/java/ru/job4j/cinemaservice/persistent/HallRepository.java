package ru.job4j.cinemaservice.persistent;

import ru.job4j.cinemaservice.model.Hall;
import ru.job4j.cinemaservice.model.Seat;

import java.util.ArrayList;
import java.util.HashMap;

public interface HallRepository {

    HashMap<Integer, ArrayList<Seat>> getSeatsByHall(Hall hall);
}
