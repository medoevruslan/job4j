package ru.job4j.cinemaservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Utility to get an ObjectMapper singleton and for converting JSON to Entity.
 */
public class StaticMapper {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final StaticMapper INSTANCE = new StaticMapper();

    private StaticMapper() { }

    public static StaticMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Convert JSON to Model.
     * @param req HttpServletRequest.
     * @param clazz Entity's class.
     * @param <T> Returned type.
     * @return One of entities.
     * @throws IOException IOException.
     */
    public <T> T convertJson(HttpServletRequest req, Class clazz) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String result;
        while ((result = reader.readLine()) != null) {
            builder.append(result);
        }
        result = builder.toString();
        return (T) MAPPER.readValue(result, clazz);
    }
}
