package ru.job4j.tracker;

import java.sql.Timestamp;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class Item {
    private static int itemCount = 0;
    private int id;
    private String name;
    private String desc;
    private long create;
    private String[] comments;

    public Item(String name, String desc, long create) {
        this.id = itemCount++;
        this.name = name;
        this.desc = desc;
        this.create = create;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getId() {
        return this.id;
    }

    public Timestamp getTime() {
        Timestamp timeS = new Timestamp(create);
        return timeS;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        boolean rst = false;
        if (this == o) {
            rst = true;
        } else if (o != null && getClass() == o.getClass()) {
            Item item = (Item) o;
            rst = name.equals(item.name) && desc.equals(item.desc);
        }
        return rst;
    }

    @Override
    public int hashCode() {
        int rst = 17;
        rst = 31 * rst + this.name.hashCode();
        rst = 31 * rst + this.desc.hashCode();
        return rst;
    }
}
