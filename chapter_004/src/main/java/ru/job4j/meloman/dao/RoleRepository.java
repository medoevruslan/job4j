package ru.job4j.meloman.dao;

import ru.job4j.meloman.entity.Role;
import ru.job4j.meloman.entity.User;

import java.util.List;

public interface RoleRepository {

    List<User> getUserByRole(Role role);

}
