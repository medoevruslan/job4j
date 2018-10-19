package ru.job4j.meloman.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Utility to get an ObjectMapper singleton and for converting JSON to Entity.
 */
public class Utils {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Utils INSTANCE = new Utils();

    private Utils() { }

    /**
     * Receive ObjectMapper.
     * @return An instance of ObjectMapper.
     */
    public static Utils getUtil() {
        return INSTANCE;
    }

    /**
     * Convert JSON to Entity.
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
