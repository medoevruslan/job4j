package ru.job4j.meloman.dao;

import ru.job4j.meloman.entity.Address;
import ru.job4j.meloman.entity.MusicType;
import ru.job4j.meloman.entity.Role;
import ru.job4j.meloman.entity.User;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    boolean addFullUser(User user);

    Role getRole(User user);

    Address getAddress(User user);

    Set<MusicType> getMusicType(User user);

    List<User> getAllEntities();
}
