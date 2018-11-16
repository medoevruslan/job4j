package ru.job4j.todolist;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.todolist.model.Item;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Supplier;

/**
 * Util manages options of seek.
 */
public class FindUtil {
    private static final FindUtil INSTANCE = new FindUtil();

    private FindUtil() { }

    public static FindUtil getInstance() {
       return INSTANCE;
    }

    /**
     * Gets Items from database by Supplier's implementation.
     * @param resp HttpServletResponse.
     * @param supplier Supplier.
     * @throws IOException IOException.
     */
    public void findItems(HttpServletResponse resp, Supplier<List<Item>> supplier) throws IOException {
        resp.setContentType("json");
        PrintWriter writer = resp.getWriter();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);
        List<Item> items = supplier.get();
        String rst = mapper.writeValueAsString(items);
        writer.write(rst);
        writer.flush();
    }
}
