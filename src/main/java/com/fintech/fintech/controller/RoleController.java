package com.fintech.fintech.controller;

import com.fintech.fintech.data.dto.rest.RoleDto;
import com.fintech.fintech.data.dto.rest.RoleUpdateDto;
import com.fintech.fintech.data.model.RoleModel;
import com.fintech.fintech.service.RoleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleModel>> getAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleModel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleModel> create(@Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.create(roleDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<RoleModel> updateById(@PathVariable Long id,
                                                @RequestBody RoleUpdateDto roleUpdateDto) {
        return ResponseEntity.ok(roleService.updateById(id, roleUpdateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
