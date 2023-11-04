package com.fintech.fintech.service.impl;

import com.fintech.fintech.data.dto.RoleDto;
import com.fintech.fintech.data.dto.RoleUpdateDto;
import com.fintech.fintech.data.entity.Role;
import com.fintech.fintech.data.model.RoleModel;
import com.fintech.fintech.data.repository.hibernate.RoleRepository;
import com.fintech.fintech.exception.AlreadyExistsException;
import com.fintech.fintech.exception.NotFoundException;
import com.fintech.fintech.mapper.RoleMapper;
import com.fintech.fintech.service.RoleService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository repository;

    @Override
    public List<RoleModel> findAll() {
        return repository.findAll().stream().map(roleMapper::mapToModel).toList();
    }

    @Override
    public RoleModel findById(Long id) {
        Optional<Role> role = repository.findById(id);

        if (role.isEmpty()) {
            log.warn("role with id '{}' doesnt exist", id);
            throw new NotFoundException("role not found");
        }

        return roleMapper.mapToModel(role.get());
    }

    @Override
    public RoleModel create(RoleDto roleDto) {
        if (repository.existsByName(roleDto.getName())) {
            log.warn("role '{}' already exists", roleDto.getName());
            throw new AlreadyExistsException("role already exists");
        }

        Role role = new Role();
        role.setName(roleDto.getName());

        return roleMapper.mapToModel(repository.save(role));
    }

    @Override
    public RoleModel updateById(Long id, RoleUpdateDto roleDto) {
        Optional<Role> roleOptional = repository.findById(id);

        if (roleOptional.isEmpty()) {
            log.warn("role with id '{}' not found", id);
            throw new NotFoundException("role not found");
        }

        Role role = roleOptional.get();

        if (StringUtils.isNoneBlank(roleDto.getName())) {
            role.setName(roleDto.getName());
        }

        return roleMapper.mapToModel(repository.save(role));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
