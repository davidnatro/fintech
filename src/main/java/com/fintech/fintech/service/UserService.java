package com.fintech.fintech.service;

import com.fintech.fintech.data.dto.rest.UserDto;
import com.fintech.fintech.data.dto.rest.UserUpdateDto;
import com.fintech.fintech.data.model.UserModel;
import java.util.List;

public interface UserService {

    List<UserModel> findAll();

    UserModel findById(Long id);

    UserModel create(UserDto userDto);

    UserModel updateById(Long id, UserUpdateDto userDto);

    void deleteById(Long id);
}
