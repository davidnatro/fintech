package com.fintech.fintech.mapper;

import com.fintech.fintech.data.entity.User;
import com.fintech.fintech.data.model.UserModel;

public interface UserMapper {

    UserModel mapToModel(User user);
}
