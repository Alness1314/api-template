package com.alness.template.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alness.template.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    @Query(value = "SELECT * FROM users WHERE email = :email AND password = :password", nativeQuery= true)
    Optional<UserEntity> getUserByEmailAndPassword(@Param ("email") String email, @Param ("password") String password);

    Optional<UserEntity> findUserByEmail(String email);
}
