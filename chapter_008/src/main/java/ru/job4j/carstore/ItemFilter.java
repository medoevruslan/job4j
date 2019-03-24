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
    private String manufacturer;
    private String model;
    private boolean byPhoto;
    private boolean currDay;
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
    private Supplier<List<Item>> byManufacturer() {
        return () -> this.manufacturer.equals("...")
                ?  this.iService.findAll()
                : this.iService.findByManufacturer(this.manufacturer);
    }

    /**
     * Filter unit.
     * @return Supplier with approriate method.
     */
    private Supplier<List<Item>> byManufacturerAndModel() {
        return () -> this.iService.findByManufacturerAndModel(this.manufacturer, this.model);
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
    private void initialize() {
        if (!this.model.equals("...")) {
            this.addFilter(this.byManufacturerAndModel());
        } else {
            this.addFilter(this.byManufacturer());
        }
        if (byPhoto) {
            this.addFilter(this.byPhotoPresence());
        }
        if (currDay) {
            this.addFilter(this.byCurrentDay());
        }
    }

    /**
     * Creates suitable filter to use.
     * @param req
     */
    private void createFilterByInput(ServletRequest req) {
        this.manufacturer = req.getParameter("manufacturer");
        this.model = req.getParameter("model");
        this.byPhoto = Boolean.valueOf(req.getParameter("byPhoto"));
        this.currDay = Boolean.valueOf(req.getParameter("day"));
    }

    /**
     * Applies the filter to the given items.
     * @param req
     * @return
     */
    public List<Item> applyFilter(ServletRequest req) {
        this.createFilterByInput(req);
        this.initialize();
        for (Supplier<List<Item>> filter: this.filters) {
            this.items = filter.get();
        }
        return this.items;
    }

    public boolean isByPhoto() {
        return byPhoto;
    }

    public void setByPhoto(boolean byPhoto) {
        this.byPhoto = byPhoto;
    }

    public boolean isCurrDay() {
        return currDay;
    }

    public void setCurrDay(boolean currDay) {
        this.currDay = currDay;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
