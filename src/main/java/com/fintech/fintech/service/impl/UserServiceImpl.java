package com.fintech.fintech.service.impl;

import com.fintech.fintech.data.dto.rest.UserDto;
import com.fintech.fintech.data.dto.rest.UserUpdateDto;
import com.fintech.fintech.data.entity.Role;
import com.fintech.fintech.data.entity.User;
import com.fintech.fintech.data.model.UserModel;
import com.fintech.fintech.data.repository.hibernate.RoleRepository;
import com.fintech.fintech.data.repository.hibernate.UserRepository;
import com.fintech.fintech.exception.AlreadyExistsException;
import com.fintech.fintech.exception.NotFoundException;
import com.fintech.fintech.mapper.UserMapper;
import com.fintech.fintech.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserModel> findAll() {
        return repository.findAll().stream().map(userMapper::mapToModel).toList();
    }

    @Override
    public UserModel findById(Long id) {
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isEmpty()) {
            log.warn("cannot find user by id '{}'", id);
            throw new NotFoundException("user not found");
        }

        return userMapper.mapToModel(userOptional.get());
    }

    @Override
    public UserModel create(UserDto userDto) {
        if (repository.existsByUsername(userDto.getUsername())) {
            log.warn("cannot create user -> '{}' already exists", userDto.getUsername());
            throw new AlreadyExistsException("user already exists");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.getRoles().add(roleRepository.findByName("USER").orElseThrow(NotFoundException::new));

        return userMapper.mapToModel(repository.save(user));
    }

    @Override
    public UserModel updateById(Long id, UserUpdateDto userDto) {
        User user = getUserWithPermissionChecking(id);

        if (StringUtils.isNoneBlank(userDto.getUsername())) {
            user.setUsername(userDto.getUsername());
        }

        if (StringUtils.isNoneBlank(userDto.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if (!CollectionUtils.isEmpty(userDto.getRoles())) {
            user.getRoles().clear();
            userDto.getRoles().forEach(r -> {
                Optional<Role> role = roleRepository.findByName(r);

                if (role.isPresent()) {
                    user.getRoles().add(role.get());
                } else {
                    log.warn("role '{}' was not found", role);
                    throw new NotFoundException("role not found");
                }
            });
        }

        return userMapper.mapToModel(repository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        getUserWithPermissionChecking(id);
        repository.deleteById(id);
    }

    private User getUserWithPermissionChecking(Long id) {
        User principal = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isEmpty()) {
            log.warn("cannot find user by id '{}'", id);
            throw new NotFoundException("user not found");
        }

        User user = userOptional.get();

        if (!principal.getUsername().equals(user.getUsername())) {
            log.warn("someone trying to access forbidden '{}' data", user.getUsername());
            throw new AccessDeniedException("forbidden");
        }

        return user;
    }
}
