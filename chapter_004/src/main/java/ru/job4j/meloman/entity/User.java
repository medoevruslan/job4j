package ru.job4j.meloman.entity;

import java.util.Set;

/**
 * User model.
 */
public class User extends Entity {
    public static final User EMPTY = new User();
    private String login;
    private String password;
    private Role role;
    private Address address;
    private Set<MusicType> musicType;

    public User() {
    }

    public User(int id, String name) {
        super(id, name);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<MusicType> getMusicType() {
        return this.musicType;
    }

    public void setMusicType(Set<MusicType> musicList) {
        this.musicType = musicList;
    }

    @Override
    public String toString() {
        return "User{"
                + "login='" + login + '\''
                + ", password='" + password + '\''
                + ", role=" + role
                + ", address=" + address
                + ", musicType=" + musicType
                + '}';
    }
}
