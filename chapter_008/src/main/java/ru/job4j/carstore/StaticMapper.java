package ru.job4j.carstore;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ObjectMapper singleton.
 */
public class StaticMapper extends ObjectMapper {
    private static final StaticMapper INSTANCE = new StaticMapper();

    private StaticMapper() { }

    public static StaticMapper getInstance() {
        return INSTANCE;
    }
}
