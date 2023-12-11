package service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.fintech.fintech.data.dto.UserDto;
import com.fintech.fintech.data.dto.UserUpdateDto;
import com.fintech.fintech.data.entity.Role;
import com.fintech.fintech.data.entity.User;
import com.fintech.fintech.data.model.UserModel;
import com.fintech.fintech.data.repository.hibernate.RoleRepository;
import com.fintech.fintech.data.repository.hibernate.UserRepository;
import com.fintech.fintech.mapper.RoleMapper;
import com.fintech.fintech.mapper.UserMapper;
import com.fintech.fintech.mapper.impl.RoleMapperImpl;
import com.fintech.fintech.mapper.impl.UserMapperImpl;
import com.fintech.fintech.service.UserService;
import com.fintech.fintech.service.impl.UserServiceImpl;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  private Random random;
  private PasswordEncoder passwordEncoder;
  private RoleMapper roleMapper;
  private UserMapper mapper;

  @Mock
  private UserRepository repository;

  @Mock
  private RoleRepository roleRepository;

  private UserService userService;

  @BeforeAll
  public void initAll() {
    this.roleMapper = new RoleMapperImpl();
    this.mapper = new UserMapperImpl(roleMapper);
    this.passwordEncoder = new BCryptPasswordEncoder();
    this.random = new SecureRandom();
  }

  @BeforeEach
  public void init() {
    this.userService = new UserServiceImpl(mapper, repository, roleRepository, passwordEncoder);
  }

  @Test
  void findAllTest() {
    List<User> users = generateListOfRandomUsers(10);

    when(repository.findAll()).thenReturn(users);

    List<UserModel> result = assertDoesNotThrow(() -> userService.findAll());

    assertNotNull(result);
    for (final User user : users) {
      result.forEach(u -> assertEquals(u.getId(), user.getId()));
    }
  }

  @Test
  void findByIdTest() {
    User user = generateUser();

    when(repository.findById(user.getId())).thenReturn(java.util.Optional.of(user));

    UserModel result = assertDoesNotThrow(() -> userService.findById(user.getId()));

    assertNotNull(result);
    assertEquals(user.getId(), result.getId());
  }

  @Test
  void createTest() {
    UserDto userDto = new UserDto();
    userDto.setUsername("username");
    userDto.setPassword("password");

    Role role = new Role();
    role.setId(random.nextLong());
    role.setName("USER");

    User user = new User();
    user.setUsername("username");
    user.setPassword(passwordEncoder.encode("password"));
    user.getRoles().add(role);

    when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));
    when(repository.save(user)).thenReturn(user);

    UserModel result = assertDoesNotThrow(() -> userService.create(userDto));

    assertNotNull(result);
    assertEquals(userDto.getUsername(), result.getUsername());
  }

  @Test
  void updateByIdTest() {
    User user = generateUser();

    UserUpdateDto userDto = new UserUpdateDto();
    userDto.setUsername("username");
    userDto.setPassword(passwordEncoder.encode("new password"));

    when(repository.findById(user.getId())).thenReturn(Optional.of(user));
    when(repository.save(user)).thenReturn(user);

    UserModel result = assertDoesNotThrow(() -> userService.updateById(user.getId(), userDto));

    assertNotNull(result);
    assertEquals(user.getId(), result.getId());
  }

  private List<User> generateListOfRandomUsers(int size) {
    List<User> users = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      users.add(generateUser());
    }
    return users;
  }

  private User generateUser() {
    User user = new User();
    user.setId(random.nextLong());
    user.setUsername("username");
    user.setPassword("password");
    return user;
  }
}
