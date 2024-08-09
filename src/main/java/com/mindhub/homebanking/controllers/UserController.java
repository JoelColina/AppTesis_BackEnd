package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.PasswordUpdateDto;
import com.mindhub.homebanking.dtos.UserDto;
import com.mindhub.homebanking.service.UserService;
import com.mindhub.homebanking.service.UtilService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private  final UserService userService;
    private final UtilService utilService;

    private static Logger log = LoggerFactory.getLogger(UserController.class);
      @Value("${configuracion.texto}")
      private String texto;

    public UserController(UserService userService,
                          UtilService utilService)
    {
        this.userService = userService;
        this.utilService = utilService;
    }

    @GetMapping
    public ResponseEntity<?> show(@RequestParam(name = "username" ) String username ) {
        return new ResponseEntity<>(this.userService.findByUsername(username) ,HttpStatus.ACCEPTED  );
    }


    @PostMapping()
    public ResponseEntity<?> post(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result .hasErrors()){
           return new ResponseEntity<>( this.utilService.errorResult(result),HttpStatus.BAD_REQUEST );
        }
      return this.userService.save(userDto);
    }

    @PostMapping("/massive")
    public ResponseEntity<?> postMassive(@Valid @RequestBody List<UserDto> listUserDto, BindingResult result) {
        if (result .hasErrors()){
            return new ResponseEntity<>( this.utilService.errorResult(result),HttpStatus.BAD_REQUEST );
        }
        return this.userService.saveAll(listUserDto);
    }

    @PutMapping( )
    public ResponseEntity<?> update(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result .hasErrors()){
            return new ResponseEntity<>( this.utilService.errorResult(result),HttpStatus.BAD_REQUEST );
        }
        return this.userService.update(userDto);
    }

    @PutMapping("/password")
    public ResponseEntity<?> update(@Valid @RequestBody PasswordUpdateDto passwordUpdateDTO, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>( this.utilService.errorResult(result),HttpStatus.BAD_REQUEST );
        }
        return this.userService.updatePass(passwordUpdateDTO);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@Valid @RequestBody UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(this.utilService.errorResult(result), HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>( userService.delete(userDto),HttpStatus.OK);
    }

    @GetMapping("/Obtener-config")
    public ResponseEntity<?> obtenerconfig(@Value("${server.port}") String puerto){
        Map<String, String> json = new HashMap<>();
        log.info(texto);
        json.put("texto", texto);
        json.put("puerto", puerto);

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

}
