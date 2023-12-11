package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fintech.fintech.WeatherApplication;
import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.repository.hibernate.CityRepository;
import jakarta.persistence.EntityExistsException;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeatherApplication.class)
public class CityRepositoryTest {

  @Container
  private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
      "postgres:12").withDatabaseName("fintech").withUsername("fintech").withPassword("fintech")
                    .withExposedPorts(5432);

  private final Random random = new SecureRandom();

  @Autowired
  private CityRepository repository;

  @BeforeAll
  public static void init() {
    postgreSQLContainer.start();
    System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
    System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
    System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
    assertTrue(postgreSQLContainer.isRunning());
  }

  @BeforeEach
  public void clearDb() {
    repository.deleteAll();
  }

  @Test
  void findAllTest() {
    List<City> cities = generateListOfRandomCities(10);
    repository.saveAll(cities);

    List<City> citiesFromDb = repository.findAll();

    assertNotNull(citiesFromDb);
    assertEquals(cities.size(), citiesFromDb.size());
    assertEquals(cities, citiesFromDb);
  }

  @Test
  public void createCityTest() {
    City city = repository.save(generateRandomCity());

    Optional<City> cityFromDb = repository.findById(city.getId());

    assertTrue(cityFromDb.isPresent());
    assertEquals(city, cityFromDb.get());
  }

  @Test
  void createAlreadyExistsCityTest() {
    City city = repository.save(generateRandomCity());

    assertThrows(EntityExistsException.class, () -> repository.save(city));
  }

  @Test
  void updateCityTest() {
    City city = repository.save(generateRandomCity());
    city.setName("New name");
    repository.save(city);

    Optional<City> cityFromDb = repository.findById(city.getId());

    assertTrue(cityFromDb.isPresent());
    assertEquals(city, cityFromDb.get());
  }

  @Test
  void deleteCityTest() {
    City city = repository.save(generateRandomCity());
    repository.delete(city);

    Optional<City> cityFromDb = repository.findById(city.getId());

    assertTrue(cityFromDb.isEmpty());
  }

  @AfterAll
  public static void destroy() {
    postgreSQLContainer.stop();
  }

  private City generateRandomCity() {
    City city = new City();
    city.setName("City " + random.nextInt());
    return city;
  }

  private List<City> generateListOfRandomCities(int size) {
    List<City> cities = new LinkedList<>();
    for (int i = 1; i <= size; i++) {
      cities.add(generateRandomCity());
    }
    return cities;
  }
}
