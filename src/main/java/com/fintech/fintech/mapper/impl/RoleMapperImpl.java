package com.fintech.fintech.mapper.impl;

import com.fintech.fintech.data.entity.Role;
import com.fintech.fintech.data.model.RoleModel;
import com.fintech.fintech.mapper.RoleMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleModel mapToModel(Role role) {
        RoleModel model = new RoleModel();
        model.setId(role.getId());
        model.setName(role.getName());
        return model;
    }
}
