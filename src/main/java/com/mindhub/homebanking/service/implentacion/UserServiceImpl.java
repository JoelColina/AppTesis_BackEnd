package com.mindhub.homebanking.service.implentacion;


import com.mindhub.homebanking.dtos.PasswordUpdateDto;
import com.mindhub.homebanking.dtos.RoleDto;
import com.mindhub.homebanking.dtos.UserDto;
import com.mindhub.homebanking.models.User;
import com.mindhub.homebanking.service.UserService;
import com.mindhub.homebanking.utils.Constants;
import com.mindhub.homebanking.utils.UsernameRandom;
import com.newton.security.userservice.domain.mapper.RoleMapper;
import com.newton.security.userservice.domain.mapper.UserMapper;
import com.newton.security.userservice.domain.service.CustomerService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.rmi.server.LogStream.log;

@Service
public class UserServiceImpl implements UserService {


    private final CustomerService customerService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final RoleMapper rolemapper = Mappers.getMapper(RoleMapper.class);
    private UserRepository UserRepository;

    private Map<String, Object> response;
    private UserDto userDtoNew;
    private User user;
    private User localUserOld;
    private  UserDto userDtoOld;
    private HttpStatus http;


    public UserServiceImpl(UserRepository userRepository,
//                                CustomerService customerService,
                                BCryptPasswordEncoder passwordEncoder) {
        this.UserRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
//        this.customerService = customerService;

    }

    @Override
    public UserDto findByUsername(String username) {
        user = new User();
        user = this.UserRepository.findByUsername(username);
        user.setPassword("");
        return userMapper.userToUserDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAll() {
        this.response = new HashMap<>();
        List<User> listuser = new ArrayList<>();
        List<UserDto> listDto = new ArrayList<>();

        try {
            listuser = this.UserRepository.findAll();
            for (User _user : listuser)
            {
                _user.setPassword("");
                List<RoleDto> roleDtoList  = rolemapper.listRoleToListRoleDto(_user.getRoles());
                userDtoNew = userMapper.userToUserDto(_user);
                userDtoNew.setRoleDto(roleDtoList);
                listDto.add(userDtoNew);

            }
            listDto.sort(Comparator.comparing  (UserDto::getFirstName ));
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USERS, listDto);
            this.http = HttpStatus.ACCEPTED;
        } catch (Exception er) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK + er.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    @Transactional
    public ResponseEntity<?> save(UserDto userDto) {
        log("inicio de operacion de creacion de usuario");
        userDtoNew = new UserDto();
        userDtoOld = new UserDto();
        String localUserName = "";
        this.response = new HashMap<>();
        try {
            while (localUserName.isEmpty()) {
                localUserName = UsernameRandom.userNameRandom(Constants.USER_GENERATE);
                userDtoNew = findByUsername(localUserName);
                if (userDtoNew != null) {
                    localUserName = "";
                }
            }
            if (this.UserRepository.findFirstByFirstNameAndSecondNameAndEmail(userDto.getFirstName(), userDto.getSecondName(), userDto.getEmail()) == null) {
                this.user = this.userMapper.userDtoToUser(userDto);
                this.user.setUsername(localUserName);
                this.user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                this.user.setEnabled(true);
                this.userDtoNew = userMapper.userToUserDto(UserRepository.save(this.user));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, userDtoNew);
                this.http = HttpStatus.CREATED;
            } else {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }
        } catch (Exception ex) {
            log (Constants.GEMERAL.ERROR +  ex.getMessage());
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, ex.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        log("fin de operacion de cracion de usuario ");
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public ResponseEntity<?> saveAll(List<UserDto> listUserDto) {
        userDtoNew = new UserDto();
        userDtoOld = new UserDto();
        String localUserName = "";
        this.response = new HashMap<>();

     for (UserDto dto :  listUserDto ){
         try {
             while (localUserName.isEmpty()) {
                 localUserName = UsernameRandom.userNameRandom(Constants.USER_GENERATE);
                 userDtoNew = findByUsername(localUserName);
                 if (userDtoNew != null) {
                     localUserName = "";
                 }
             }
             if (this.UserRepository.findFirstByFirstNameAndSecondNameAndEmail(dto.getFirstName(), dto.getSecondName(), dto.getEmail()) == null) {
                 this.user = this.userMapper.userDtoToUser(dto);
                 this.user.setUsername(localUserName);
                 this.user.setPassword(passwordEncoder.encode(dto.getPassword()));
                 this.user.setEnabled(true);
                 this.userDtoNew = userMapper.userToUserDto(UserRepository.save(this.user));
                 this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                 this.response.put(Constants.USER.USER, userDtoNew);
                 this.http = HttpStatus.CREATED;
             } else {
//                 this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
//                 this.response.put(Constants.GEMERAL.FAIL, dto.getFirstName() +" "+ dto.getSecondName() +" "+ dto.getEmail()   );
                 this.http = HttpStatus.CONFLICT;
             }
         } catch (Exception ex) {
             this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
             this.response.put(Constants.GEMERAL.ERROR, ex.getMessage());
             this.http = HttpStatus.BAD_REQUEST;
         }
         localUserName = "";
     }
     return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    @Transactional
    public ResponseEntity<?> updatePass(PasswordUpdateDto passwordUpdateDTO) {
        userDtoNew = new UserDto();
        userDtoOld = new UserDto();
        this.response = new HashMap<>();
        try {
            localUserOld = findByUsernamePassword(passwordUpdateDTO.getPassword());
            if(passwordEncoder.matches(passwordUpdateDTO.getOldPassword(),localUserOld.getPassword())) {
                    localUserOld.setPassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));
                    userDtoNew = userMapper.userToUserDto(
                            this.UserRepository.save(localUserOld)
                    );
                    this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                    this.http = HttpStatus.ACCEPTED;
            } else {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }
        } catch (Exception er) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, er.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(UserDto localUserDto) {
        userDtoOld = new UserDto();
        userDtoNew = new UserDto();
        this.response = new HashMap<>();
        try {
            localUserOld = findByUsernamePassword(localUserDto.getUsername());
            if (localUserOld == null) {
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            } else {
                localUserOld.setFirstName(localUserDto.getFirstName());
                localUserOld.setSecondName(localUserDto.getSecondName());
                localUserOld.setEmail(localUserDto.getEmail());
                localUserOld.setPassword(localUserOld.getPassword());
                localUserOld.setEnabled(localUserDto.isEnabled());
                userDtoNew = userMapper.userToUserDto(this.UserRepository.save(localUserOld));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, userDtoNew);
                this.http = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    @Transactional
    public ResponseEntity<?> delete(UserDto localUserDto) {
        userDtoOld = new UserDto();
        userDtoNew = new UserDto();
        this.response = new HashMap<>();
        try {
            userDtoOld = findByUsername(localUserDto.getUsername());
            if (userDtoOld == null) {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.NOT_FOUND;
            } else {
                userDtoOld.setEnabled(false);
                this.UserRepository.save(this.userMapper.userDtoToUser(userDtoOld));
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.http = HttpStatus.ACCEPTED;
            }
        } catch (Exception er) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK + er.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    private  User findByUsernamePassword(String username) {
        user = new User();
        user = this.UserRepository.findByUsername(username);
         return user;
    }

}



