package ru.job4j.carstore;

import ru.job4j.carstore.dao.ItemDAO;
import ru.job4j.carstore.entity.Item;
import ru.job4j.carstore.service.ItemService;

import javax.servlet.ServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Supplementary class filters items.
 */
public class ItemFilter {
    private final ArrayList<Supplier> filters = new ArrayList<>();
    private static final ItemFilter INSTANCE = new ItemFilter();
    private final ItemDAO iService = ItemService.getInstance();

    private List<Item> items = new ArrayList<>();

    private ItemFilter() { }

    public static ItemFilter getInstance() {
        return INSTANCE;
    }

    /**
     * Filter unit.
     * @return Supplier with approriate method.
     */
    private Supplier<List<Item>> byManufacturer(String manufacturer) {
        return () -> manufacturer.equals("...")
                ?  this.iService.findAll()
                : this.iService.findByManufacturer(manufacturer);
    }

    /**
     * Filter unit.
     * @return Supplier with approriate method.
     */
    private Supplier<List<Item>> byManufacturerAndModel(String manufacturer, String model) {
        return () -> this.iService.findByManufacturerAndModel(manufacturer, model);
    }

    /**
     * Filter unit.
     * @return Supplier with approriate method.
     */
    private Supplier<List<Item>> byPhotoPresence() {
        return () -> this.items.stream()
                .filter(item -> item.getImages().size() != 0)
                .collect(Collectors.toList());
    }

    /**
     * Filter unit.
     * @return Supplier with approriate method.
     */
    private Supplier<List<Item>> byCurrentDay() {
        LocalDateTime now = LocalDateTime.now().with(LocalTime.MIN);
        return () -> this.items.stream()
                .filter(item -> !now.isAfter(item.getCreated().toLocalDateTime()))
                .collect(Collectors.toList());
    }

    /**
     * Add new filter to list.
     * @param filter Filter.
     */
    private void addFilter(Supplier<List<Item>> filter) {
        this.filters.add(filter);
    }

    /**
     * Fills the list with essential filters.
     */
    private void initialize(ServletRequest req) {
        String manufacturer = req.getParameter("manufacturer");
        String model = req.getParameter("model");
        boolean byPhoto = Boolean.valueOf(req.getParameter("byPhoto"));
        boolean currDay = Boolean.valueOf(req.getParameter("day"));
        if (!req.getParameter("model").equals("...")) {
            this.addFilter(this.byManufacturerAndModel(manufacturer, model));
        } else {
            this.addFilter(this.byManufacturer(manufacturer));
        }
        if (byPhoto) {
            this.addFilter(this.byPhotoPresence());
        }
        if (currDay) {
            this.addFilter(this.byCurrentDay());
        }
    }

    /**
     * Applies the filter to the given items.
     * @param req
     * @return
     */
    public List<Item> applyFilter(ServletRequest req) {
        this.initialize(req);
        for (Supplier<List<Item>> filter: this.filters) {
            this.items = filter.get();
        }
        return this.items;
    }
}
