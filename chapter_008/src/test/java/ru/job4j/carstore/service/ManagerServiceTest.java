package ru.job4j.carstore.service;

import org.junit.Test;
import ru.job4j.carstore.dao.DAO;
import ru.job4j.carstore.entity.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test of all CRUD operations for all entities.
 */

public class ManagerServiceTest {

    @Test
    public void testAllCrudWithAllEntitiesConsistently() {
        DAO<Manufacturer> manufactService = ManufacturerService.getInstance();

        Manufacturer testManufacturer = new Manufacturer("Audi");

        manufactService.add(testManufacturer);

        DAO<Model> modelService = ModelService.getInstance();

        Model testModel = new Model("TT");
        testModel.setManufacturer(testManufacturer);

        modelService.add(testModel);

        DAO<Engine> engineService = EngineService.getInstance();

        Engine testEngine = new Engine(2.3f);

        engineService.add(testEngine);

        DAO<Body> bodyService = BodyService.getInstance();

        Body testBody = new Body();

        bodyService.add(testBody);

        DAO<Transmission> transmService = TransmissionService.getInstance();

        Transmission testTransmission = new Transmission();

        transmService.add(testTransmission);

        DAO<Car> carService = CarService.getInstance();

        Car testCar = new Car(testManufacturer.getName(), testModel.getName(), testTransmission.getType(), String.valueOf(testEngine.getCapacity()), testBody.getType());
        assertThat(carService.findAll().size(), is(0));

        carService.add(testCar);

        testModel.setName("100");
        modelService.update(testModel);

        Model model = manufactService.findById(testManufacturer.getId()).getModels().get(0);
        assertThat(model.getName(), is("100"));

        assertThat(carService.findAll().size(), is(1));

        DAO<User> userService = UserService.getInstance();

        User testUser = new User("testLogin", "testPassword", "testEmail");
        userService.add(testUser);

        DAO<Item> itemService = ItemService.getInstance();

        Item testItem = new Item();
        testItem.setUser(testUser);
        testItem.setCar(testCar);
        itemService.add(testItem);

        DAO<Image> imageService = ImageService.getInstance();

        Image testImage = new Image("testImage", "testPath", testItem);
        imageService.add(testImage);

        Item item = itemService.findById(testItem.getId());

        assertThat(item.getCar().getManufacturer(), is("Audi"));
        assertThat(item.getUser().getLogin(), is("testLogin"));
        assertThat(item.getImages().get(0).getName(), is("testImage"));
    }
}