package com.alness.template.user.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alness.template.role.entity.RoleEntity;
import com.alness.template.role.repository.RoleRepository;
import com.alness.template.user.dto.RequestUserDto;
import com.alness.template.user.dto.ResponseUserDto;
import com.alness.template.user.entity.UserEntity;
import com.alness.template.user.repository.UserRepository;
import com.alness.template.user.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UsersService, UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void init(){
        mapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findUserByEmail(username);
        if(!user.isPresent()){
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        }else{
            log.info("User found in the database {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        user.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.get().getEmail(), user.get().getPassword(), authorities);
    }

    @Override
    public ResponseUserDto findOne(String id) {
        Optional<UserEntity> user = userRepository.findById(UUID.fromString(id));
        if(!user.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id: "+ id +" not found");
        }
        return getUser(user.get());
    }

    @Override
    public List<ResponseUserDto> findAll() {
        List<UserEntity> users = userRepository.findAll();
        List<ResponseUserDto> result = new ArrayList<ResponseUserDto>();
        for (UserEntity user : users) {
            result.add(getUser(user));
        }
        return result;
    }

    @Override
    public ResponseUserDto save(RequestUserDto user) {
        UserEntity newUser = mapper.map(user, UserEntity.class);
        newUser.setCreatedAt(LocalDateTime.now());
        //set roles
        if(user.getRoles().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "role not present");
        }
        Optional<RoleEntity> role = roleRepository.findByName(user.getRoles());
        if(!role.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "role not found, contact to admin");
        }
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(role.get());
        newUser.setRoles(roles);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            newUser = userRepository.save(newUser);
        } catch (Exception e) {
            log.info("Error saving user: {}", e.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Problem with saving user: "+e.getLocalizedMessage());
        }
        return getUser(newUser);
    }

    @Override
    public ResponseUserDto update(String id, RequestUserDto user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    private ResponseUserDto getUser(UserEntity source){
        return mapper.map(source, ResponseUserDto.class);
    }
    
}
