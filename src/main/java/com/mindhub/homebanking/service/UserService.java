package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.PasswordUpdateDto;
import com.mindhub.homebanking.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto findByUsername(String username);
    ResponseEntity<?> findAll();
    ResponseEntity<?> save(UserDto userDto);
    ResponseEntity<?> saveAll(List<UserDto> userDto);
    ResponseEntity<?> update(UserDto userDto);
    ResponseEntity<?> delete(UserDto userDto);
    ResponseEntity<?> updatePass(PasswordUpdateDto passwordUpdateDTO);
}
