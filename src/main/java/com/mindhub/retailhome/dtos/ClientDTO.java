package com.mindhub.retailhome.dtos;

import com.mindhub.retailhome.models.Client;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ClientDTO {

    private String names;
    private String lastName;
    private String motherLastName;
    private String ruth;
    private Date   birthDate;
    private Number telephoneNumber;
    private String email;
    private Number totalLimit;
    private Number debtAccount;
    private Number availableSpace;
    private String idClient;
    private String username;
    private String password;




}
