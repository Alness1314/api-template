package com.alness.template.role.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alness.template.role.dto.RequestRoleDto;
import com.alness.template.role.dto.ResponseRoleDto;
import com.alness.template.role.entity.RoleEntity;
import com.alness.template.role.repository.RoleRepository;
import com.alness.template.role.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void init(){
        mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public ResponseRoleDto findOne(String id) {
        Optional<RoleEntity> role = roleRepository.findById(UUID.fromString(id));
        if(!role.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role with id: "+ id +" not found");
        }
        return getRole(role.get());
    }

    @Override
    public ResponseRoleDto findByName(String name) {
        Optional<RoleEntity> role = roleRepository.findByName(name);
        if(!role.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role with name: "+ name +" not found");
        }
        return getRole(role.get());
    }

    @Override
    public List<ResponseRoleDto> findAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        List<ResponseRoleDto> result = new ArrayList<ResponseRoleDto>();
        for (RoleEntity role : roles) {
            result.add(getRole(role));
        }
        return result;
    }

    @Override
    public ResponseRoleDto save(RequestRoleDto role) {
        log.info("ROLE DTO: {}", role.getName());
        RoleEntity newRole = mapper.map(role, RoleEntity.class);
        log.info("ROLE ENT: {}", newRole.getName());
        newRole.setCreateAt(LocalDateTime.now());
        try {
            newRole = roleRepository.save(newRole);
        } catch (Exception e) {
            log.info("Error saving role: {}", e.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Error saving role: "+e.getLocalizedMessage());
        }
        return getRole(newRole);
    }

    @Override
    public ResponseRoleDto update(String id, RequestRoleDto user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    private ResponseRoleDto getRole(RoleEntity source){
        return mapper.map(source, ResponseRoleDto.class);
    }

    
    
}
