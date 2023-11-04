package com.fintech.fintech.service;

import com.fintech.fintech.data.dto.RoleDto;
import com.fintech.fintech.data.dto.RoleUpdateDto;
import com.fintech.fintech.data.model.RoleModel;
import java.util.List;

public interface RoleService {

    List<RoleModel> findAll();

    RoleModel findById(Long id);

    RoleModel create(RoleDto roleDto);

    RoleModel updateById(Long id, RoleUpdateDto roleDto);

    void deleteById(Long id);
}
