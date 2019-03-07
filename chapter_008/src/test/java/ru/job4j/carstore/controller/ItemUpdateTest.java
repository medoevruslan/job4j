package ru.job4j.carstore.controller;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemUpdateTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private ItemUpdate itemUpdate;
    private DAO<Item> service;

    @Before
    public void setup() {
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.itemUpdate = new ItemUpdate();
        this.service = ItemService.getInstance();
    }

    @Test
    public void whenItemUpdateThenItUpdated() {
        Item item = new Item();
        item.setSold(false);
        this.service.add(item);

        when(this.req.getParameter("isSold")).thenReturn("true");
        when(this.req.getParameter("id")).thenReturn("1");

        this.itemUpdate.doPost(this.req, this.resp);

        assertThat(this.service.findById(1).getSold(), is(true));
    }
}
