package ru.job4j.todolist.model;

import java.sql.Timestamp;

public class Item {
    private int id;
    private String desc;
    private Timestamp created;
    private boolean done;

    public Item() {
        this.done = false;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Item(int id, boolean done) {
        this.id = id;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        } else {
            if (o != null && getClass() == o.getClass()) {
                Item item = (Item) o;
                result = this.id == item.id && this.desc.equals(item.desc);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.id;
        result = 31 * result + (this.desc != null ? this.desc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", done=" + done
                + '}';
    }
}
