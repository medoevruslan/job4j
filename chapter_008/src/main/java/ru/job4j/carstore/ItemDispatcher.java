package ru.job4j.carstore;

import org.apache.commons.fileupload.FileItem;
import ru.job4j.carstore.entity.*;
import ru.job4j.carstore.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class create new car and set all data to it.
 */
public class ItemDispatcher {
    private static final ItemDispatcher INSTANCE = new ItemDispatcher();

    private ItemDispatcher() { }

    public static ItemDispatcher getInstance() {
        return INSTANCE;
    }

    public void createItem(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        Car car = new Car();
        car.setManufacturer(req.getParameter("manufacturer"));
        car.setModel(req.getParameter("model"));
        car.setBody(req.getParameter("body_type"));
        car.setTransmission(req.getParameter("transmission"));
        car.setEngine(req.getParameter("engine"));
        CarService.getInstance().add(car);
        String header = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        Item item = new Item(header, description, new Timestamp(System.currentTimeMillis()), user, car, false, price);
        this.setImagesToItem(req, item);
    }

//    private Engine createEngine(Float capacity) {
//        Engine engine = new Engine(capacity);
//        DAO service = EngineService.getInstance();
//        int id = service.add(engine);
//        return (Engine) service.findById(id);
//
//    }

    private void setImagesToItem(HttpServletRequest req, Item item) {
        ItemService.getInstance().add(item);
        List<Image> images = new ArrayList<>();
        List<FileItem> files = (ArrayList<FileItem>) req.getAttribute("upload");
        try {
            for (FileItem fileItem : files) {
                if (fileItem != null) {
                    String fileName = this.createFilename();
                    String filePath = this.createUploadDir(req) + File.separator + fileName;
                    Image image = new Image(fileName, filePath, item);
                    ImageService.getInstance().add(image);
                    images.add(image);
                    fileItem.write(new File(filePath));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createFilename() {
        int prefix = new Random().nextInt(100) + 1;
        int suffix = new Random().nextInt(100) + 1;
        return String.format("%dimage%d.jpg", prefix, suffix);
    }

    private String createUploadDir(HttpServletRequest req) {
        String imagePath = "images";
        String uploadPath = req.getServletContext().getRealPath("") + imagePath;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        return uploadPath;
    }
}
