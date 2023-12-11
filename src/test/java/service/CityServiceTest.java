package service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fintech.fintech.config.property.CacheProperty;
import com.fintech.fintech.data.component.HibernateDataComponent;
import com.fintech.fintech.data.entity.City;
import com.fintech.fintech.service.HibernateCrudService;
import com.fintech.fintech.service.impl.hibernate.HibernateCityServiceImpl;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private HibernateDataComponent<City, Long> cityDataComponent;

    @Mock
    private CacheProperty cacheProperty;

    private Map<Long, City> weatherCache;

    private HibernateCrudService<City, Long> service;

    @BeforeEach
    public void init() {
        this.service = spy(new HibernateCityServiceImpl(cacheProperty, cityDataComponent));

        when(cacheProperty.getSize()).thenReturn(10);
    }

    @Test
    void getFromDbTest() {
        long id = 1;

        City city = new City();
        city.setId(id);
        city.setName("City");

        when(cityDataComponent.findById(id)).thenReturn(city);

        assertDoesNotThrow(() -> service.findById(id));

        verify(cityDataComponent, times(1)).findById(id);
    }
}
