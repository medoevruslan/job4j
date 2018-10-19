package ru.job4j.meloman.service;

import ru.job4j.meloman.dao.UserDAO;
import ru.job4j.meloman.entity.Address;
import ru.job4j.meloman.entity.MusicType;
import ru.job4j.meloman.entity.Role;
import ru.job4j.meloman.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A stub to make the tests.
 */
public class UserServiceStub implements UserDAO {
    private final ArrayList<User> users = new ArrayList<>();
    private int index = 0;

    @Override
    public boolean add(User model) {
        boolean rst = true;
        for (User user : this.users) {
            if (model.getLogin().equals(user.getLogin())) {
                rst = false;
                break;
            }
         }
         if (rst) {
            model.setId(this.index++);
            this.users.add(model);
         }
        return rst;
    }

    @Override
    public boolean remove(User model) {
        return  this.users.remove(model);
    }

    @Override
    public boolean update(User data) {
        boolean rst = false;
        for (int idx = 0; idx < this.users.size(); idx++) {
            if (data.getId() == this.users.get(idx).getId()) {
                data.setId(this.users.get(idx).getId());
                this.users.set(idx, data);
                rst = true;
                break;
            }
        }
        return rst;
    }

    @Override
    public User findById(int id) {
        User user = User.EMPTY;
        for (User usr : this.users) {
            if (id == usr.getId()) {
                user = usr;
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public boolean addFullUser(User user) {
        return this.add(user);
    }

    @Override
    public Role getRole(User user) {
        return user.getRole();
    }

    @Override
    public Address getAddress(User user) {
        return user.getAddress();
    }

    @Override
    public Set<MusicType> getMusicType(User user) {
        return user.getMusicType();
    }

    @Override
    public List<User> getAllEntities() {
        return this.findAll();
    }
}
