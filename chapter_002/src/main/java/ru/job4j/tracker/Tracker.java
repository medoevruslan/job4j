package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

/**
 * Хранилище заявок.
 */

public class Tracker implements AutoCloseable {
    private final Logger log = LoggerFactory.getLogger(Tracker.class);
    private Connection connection;

    /**
     * Конструктор инициализирующий соединение с базой данных.
     * В случае отсутсвия необходимой таблицы - загружает её из скрипта в файле конфигурации.
     * @param file файл конфигурации .
     * @throws Exception
     */
    public Tracker(File file) throws Exception  {
        Properties props = new Properties();
        FileReader reader = new FileReader(file);
        props.load(reader);
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String createTable = props.getProperty("table");
        connection = DriverManager.getConnection(url, user, password);
        if (!isExist()) {
            Statement st = connection.createStatement();
            Path path = Paths.get(createTable);
            st.executeUpdate(new String(Files.readAllBytes(path), "UTF-8"));
        }
    }

    /**
     * Метод проверяет наличие необходимой таблицы для хранения заявок.
     * @return true or false.
     */
    private boolean isExist() {
        boolean rst = false;
        try {
            DatabaseMetaData data = connection.getMetaData();
            ResultSet res = data.getTables(null, null, "items", new String[]{"TABLE"});
            rst = res.next();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return rst;
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        try (PreparedStatement st = connection.prepareStatement("insert into items(name, desc, date) values(?, ?, ?)")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            st.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Метод удаляет заявку по заданому id.
     * @param id Уникальный номер заявки.
     */
    public void delete(String id) {
        try (PreparedStatement st = connection.prepareStatement("delete from items where id = ? ")) {
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Метод заменяет заявку на другую.
     * @param id Уникальный номер заявки.
     * @param item Заявку котороую необходимо вставить.
     */
    public void replace(String id, Item item) {
        try (PreparedStatement st = connection.prepareStatement("upate items set name = ?, desc = ?, date = ? where id = ?")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setTimestamp(3, item.getTime());
            st.setString(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     *  Метод возвращает список всех заявок.
     * @return Список все заявок.
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> items = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rst = stmt.executeQuery("select * from items");
            while (rst.next()) {
                String name = rst.getString("name");
                String desc = rst.getString("desc");
                Timestamp time = rst.getTimestamp("date");
                items.add(new Item(name, desc, time.getTime()));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return items;
    }

    /**
     * Метод производид поиск по хранилищу по заданному имени.
     * @param key Имя искомого элемнта.
     * @return Список всех заявок с искомым именем.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> items = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("select * from items where name = ?")) {
            stmt.setString(1, key);
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
            while (rst.next()) {
                String name = rst.getString("name");
                String desc = rst.getString("desc");
                Timestamp time = rst.getTimestamp("date");
                items.add(new Item(name, desc, time.getTime()));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return items;
    }

    /**
     * Метод производит поиск по спику по заданному Id.
     * @param id Уникальный номер заявки.
     * @return null или искомую заявку.
     */

    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement stmt = connection.prepareStatement("select * from items where id = ?")) {
            stmt.setString(1, id);
            stmt.execute();
            ResultSet rst = stmt.getResultSet();
            while (rst.next()) {
                String name = rst.getString("name");
                String desc = rst.getString("desc");
                Timestamp time = rst.getTimestamp("date");
                item = new Item(name, desc, time.getTime());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public void close() throws Exception { }
}
