package ru.job4j.tracker;

public class Item {

    private String id;
    private String name;
    private String desc;
    private long create;
    private String[] comments;

    public Item(String name, String desc, long create) {
        this.name = name;
        this.id = id;
        this.desc = desc;
        this.create = create;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
