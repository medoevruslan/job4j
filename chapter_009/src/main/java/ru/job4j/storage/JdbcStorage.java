package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.job4j.storage.model.User;

import javax.sql.DataSource;
import java.util.List;

@Component("jdbcStorage")
public class JdbcStorage implements Storage<User> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcStorage(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int add(User user) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate);
        insert.withTableName("userstorage")
                .usingColumns("name", "surname")
                .usingGeneratedKeyColumns("id");
        return insert.executeAndReturnKey(new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname()))
                .intValue();
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM userstorage", new BeanPropertyRowMapper<>(User.class));
    }
}
