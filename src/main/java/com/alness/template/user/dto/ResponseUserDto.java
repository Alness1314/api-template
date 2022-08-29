package com.alness.template.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.alness.template.role.dto.ResponseRoleDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDto {
    private String id;
    private String email;
    private String password;
    private String detail;
    private List<ResponseRoleDto> roles;
    private boolean status;
    private LocalDateTime createdAt;
}
