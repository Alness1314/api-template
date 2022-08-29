package com.alness.template.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alness.template.role.dto.RequestRoleDto;
import com.alness.template.role.dto.ResponseRoleDto;
import com.alness.template.role.service.RoleService;


@RestController
@RequestMapping("${apiPrefix}/role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping
    public ResponseEntity<List<ResponseRoleDto>> getAllRoles(){
        List<ResponseRoleDto> roles = roleService.findAll();
        return new ResponseEntity<List<ResponseRoleDto>>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRoleDto> getOneRole(@RequestParam String id){
        ResponseRoleDto role = roleService.findOne(id);
        return new ResponseEntity<ResponseRoleDto>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseRoleDto> createRole(@RequestBody RequestRoleDto role){
        ResponseRoleDto newRole = roleService.save(role);
        return new ResponseEntity<ResponseRoleDto>(newRole, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateRole(){
        String user = "actualizas un usuario";
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRole(){
        String user = "borras un usuario";
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
