package com.alness.template.user.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.alness.template.role.entity.RoleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @Type(type = "uuid-char")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "nvarchar(36)")
    private UUID id = UUID.randomUUID();

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "nvarchar(255)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "nvarchar(255)")
    private String password;
    /*@OneToOne(() => Detail)
    @JoinColumn()*/
    @Column(name = "detail", columnDefinition = "nvarchar(255)")
    private String detail;

    //@JoinTable({name: 'user_role'})
    @ManyToMany(fetch = FetchType.EAGER)
    //@Column(name = "roles", nullable = false, columnDefinition = "nvarchar(64)")
    private List<RoleEntity> roles = new ArrayList<>();

    @Column(name = "status", columnDefinition = "tinyint(1)")
    private boolean status = true;

    @Column(name = "created_at", columnDefinition = "timestamp")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
