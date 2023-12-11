package component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.component.impl.hibernate.HibernateCityDataComponentImpl;
import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.data.repository.hibernate.CityRepository;
import com.fintech.fintech.exception.AlreadyExistsException;
import com.fintech.fintech.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityDataComponentTest {

  @Mock
  private CityRepository cityRepository;

  private HibernateDataComponent<City, Long> dataComponent;

  @BeforeEach
  public void init() {
    this.dataComponent = new HibernateCityDataComponentImpl(cityRepository);
  }

  @Test
  void findAllTest() {
    List<City> cities = List.of(new City());

    when(cityRepository.findAll()).thenReturn(cities);

    List<City> result = dataComponent.findAll();

    assertEquals(cities, result);

    verify(cityRepository, times(1)).findAll();
  }

  @Test
  void findByIdTest() {
    City city = new City();
    city.setId(1L);

    when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

    City result = dataComponent.findById(1L);

    assertEquals(city, result);

    verify(cityRepository, times(1)).findById(1L);
  }

  @Test
  void findByIdNotFoundTest() {
    when(cityRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> dataComponent.findById(1L));

    verify(cityRepository, times(1)).findById(1L);
  }

  @Test
  void createTest() {
    long id = 1;
    City city = new City();
    city.setId(id);

    when(cityRepository.existsById(id)).thenReturn(false);

    assertDoesNotThrow(() -> dataComponent.create(city));

    verify(cityRepository, times(1)).save(city);
    verify(cityRepository, times(1)).existsById(id);
  }

  @Test
  void createAlreadyExistsTest() {
    long id = 1;
    City city = new City();
    city.setId(id);

    when(cityRepository.existsById(id)).thenReturn(true);

    assertThrows(AlreadyExistsException.class, () -> dataComponent.create(city));

    verify(cityRepository, times(0)).save(city);
    verify(cityRepository, times(1)).existsById(id);
  }

  @Test
  void updateTest() {
    long id = 1;
    City city = new City();
    city.setId(id);

    when(cityRepository.existsById(id)).thenReturn(true);

    assertDoesNotThrow(() -> dataComponent.update(1L, city));

    verify(cityRepository, times(1)).save(city);
  }

  @Test
  void updateNotFoundTest() {
    long id = 1;
    City city = new City();
    city.setId(id);

    when(cityRepository.existsById(id)).thenReturn(false);

    assertThrows(NotFoundException.class, () -> dataComponent.update(1L, city));

    verify(cityRepository, times(0)).save(city);
  }
}
