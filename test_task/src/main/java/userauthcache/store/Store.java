package userauthcache.store;

import userauthcache.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Store for users.
 */
public class Store {
    private static final Store INSTANCE = new Store();
    private final ArrayList<User> users = new ArrayList<>();
    private int id = 0;

    private Store() { }

    public static Store getInstance() {
        return INSTANCE;
    }

    /**
     * Adds new user.
     * @param user User.
     * @return Added user.
     */
    public User add(User user) {
        user.setId(id++);
        this.users.add(user);
        return user;
    }

    /**
     * Deletes user.
     */
    public void delete(User user) {
        this.users.remove(user);
    }

    /**
     * Searh user by id.
     * @param id User's id.
     * @return Found user.
     */
    public User findById(int id) {
        User user = new User();
        for (User usr : users) {
            if (usr.getId() == id) {
                user = usr;
                break;
            }
        }
        return user;
    }

    /**
     * Get all users.
     * @return User's list.
     */
    public List<User> findAll() {
        return this.users;
    }
}
