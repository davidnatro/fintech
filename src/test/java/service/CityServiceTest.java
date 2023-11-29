package service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fintech.fintech.config.property.CacheProperty;
import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.component.impl.hibernate.HibernateCityDataComponentImpl;
import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.repository.hibernate.CityRepository;
import com.fintech.fintech.exception.NotFoundException;
import com.fintech.fintech.service.HibernateCrudService;
import com.fintech.fintech.service.impl.hibernate.HibernateCityServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

  @Mock
  private CacheProperty cacheProperty;

  @Mock
  private HibernateDataComponent<City, Long> dataComponent;

  private HibernateCrudService<City, Long> service;

  @BeforeEach
  public void init() {
    this.service = spy(new HibernateCityServiceImpl(cacheProperty, dataComponent));
  }

  @Test
  void findAllTest() {
    List<City> cities = new ArrayList<>();

    when(dataComponent.findAll()).thenReturn(cities);

    List<City> result = assertDoesNotThrow(() -> service.findAll());

    assertEquals(cities, result);

    verify(dataComponent, times(1)).findAll();
    verify(service, times(1)).findAll();
  }

  @Test
  void findByIdTest() {
    long id = 1L;
    City city = new City();

    when(dataComponent.findById(id)).thenReturn(city);

    City result = assertDoesNotThrow(() -> service.findById(id));

    assertEquals(city, result);

    verify(dataComponent, times(1)).findById(id);
    verify(service, times(1)).findById(id);
  }

  @Test
  void findNonExistingByIdTest() {
    long id = 1L;

    when(dataComponent.findById(id)).thenThrow(NotFoundException.class);

    assertThrows(NotFoundException.class, () -> service.findById(id));

    verify(dataComponent, times(1)).findById(id);
    verify(service, times(1)).findById(id);
  }

  @Test
  void createTest() {
    City city = new City();

    assertDoesNotThrow(() -> service.create(city));

    verify(dataComponent, times(1)).create(city);
    verify(service, times(1)).create(city);
  }

  @Test
  void updateTest() {
    long id = 1L;
    City city = new City();

    assertDoesNotThrow(() -> service.update(id, city));

    verify(dataComponent, times(1)).update(id, city);
    verify(service, times(1)).update(id, city);
  }

  @Test
  void deleteByIdTest() {
    long id = 1L;

    assertDoesNotThrow(() -> service.deleteById(id));

    verify(dataComponent, times(1)).deleteById(id);
    verify(service, times(1)).deleteById(id);
  }
}
