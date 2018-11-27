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
        manufactService.deleteAll();

        Manufacturer testManufacturer = new Manufacturer("Audi", "TT");

        manufactService.add(testManufacturer);

        DAO<Engine> engineService = EngineService.getInstance();
        engineService.deleteAll();

        Engine testEngine = new Engine();

        engineService.add(testEngine);

        DAO<Body> bodyService = BodyService.getInstance();
        bodyService.deleteAll();

        Body testBody = new Body();

        bodyService.add(testBody);

        DAO<Transmission> transmService = TransmissionService.getInstance();
        transmService.deleteAll();

        Transmission testTransmission = new Transmission();

        transmService.add(testTransmission);

        DAO<Car> carService = CarService.getInstance();
        carService.deleteAll();

        Car testCar = new Car(testManufacturer, testTransmission, testEngine, testBody);
        assertThat(carService.findAll().size(), is(0));

        carService.add(testCar);
        assertThat(carService.findAll().size(), is(1));

        testManufacturer.setModel("100");
        manufactService.update(testManufacturer);
        Manufacturer manufacturer = manufactService.findById(testManufacturer.getId());
        assertThat(manufacturer.getModel(), is("100"));
        assertThat(carService.findAll().get(0).getManufacturer().getModel(), is("100"));

        carService.delete(testCar);
        assertThat(carService.findAll().size(), is(0));
    }
}