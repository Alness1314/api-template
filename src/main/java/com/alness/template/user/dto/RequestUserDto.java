package com.alness.template.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDto {
    private String email;
    private String password;
    private String detail;
    private String roles;
}
