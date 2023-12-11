package com.fintech.fintech.mapper.impl;

import com.fintech.fintech.data.entity.User;
import com.fintech.fintech.data.model.UserModel;
import com.fintech.fintech.mapper.RoleMapper;
import com.fintech.fintech.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper;

    @Override
    public UserModel mapToModel(User user) {
        UserModel model = new UserModel();

        model.setId(user.getId());
        model.setUsername(user.getUsername());
        model.setRoles(user.getRoles()
                               .stream()
                               .map(roleMapper::mapToModel)
                               .toList());

        return model;
    }
}
