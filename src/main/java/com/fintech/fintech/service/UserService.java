package com.fintech.fintech.service;

import com.fintech.fintech.data.dto.UserDto;
import com.fintech.fintech.data.dto.UserUpdateDto;
import com.fintech.fintech.data.model.UserModel;
import java.util.List;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;

public interface UserService {

    List<UserModel> findAll();

    UserModel findById(Long id);

    UserModel create(UserDto userDto);

    UserModel updateById(Long id, UserUpdateDto userDto);

    void deleteById(Long id);
}
