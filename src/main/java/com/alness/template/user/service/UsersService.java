package com.alness.template.user.service;

import java.util.List;

import com.alness.template.user.dto.RequestUserDto;
import com.alness.template.user.dto.ResponseUserDto;

public interface UsersService {
    public ResponseUserDto findOne(String id);
    public List<ResponseUserDto> findAll();
    public ResponseUserDto save(RequestUserDto user);
    public ResponseUserDto update(String id, RequestUserDto user);
    public void delete(String id);
}
