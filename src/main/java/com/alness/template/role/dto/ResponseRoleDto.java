package com.alness.template.role.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRoleDto {
    private String id;
    private String name;
    private Boolean status;
    private LocalDateTime createAt;
}
