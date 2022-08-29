package com.alness.template.role.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "nvarchar(36)")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "nvarchar(64)")
    private String name;

    @Column(name = "status", columnDefinition = "tinyint(1)")
    private boolean status = true;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private LocalDateTime createAt;
}
