package com.alness.template.role.service;

import java.util.List;

import com.alness.template.role.dto.RequestRoleDto;
import com.alness.template.role.dto.ResponseRoleDto;

public interface RoleService {
    public ResponseRoleDto findOne(String id);
    public ResponseRoleDto findByName(String name);
    public List<ResponseRoleDto> findAll();
    public ResponseRoleDto save(RequestRoleDto user);
    public ResponseRoleDto update(String id, RequestRoleDto user);
    public void delete(String id);
}
