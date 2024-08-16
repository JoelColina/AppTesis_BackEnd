package com.mindhub.homebanking.service.implentacion;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.mappers.HomeBankingMapper;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.service.ClientService;
import com.mindhub.homebanking.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.rmi.server.LogStream.log;

@Service
public class ClientServiceImpl implements ClientService {

    private HomeBankingMapper homeBankingMapper;
    private ClientRepository clientRepository;

    private Map<String, Object> response;
    private ClientDTO clientDtoOld;
    private HttpStatus http;
    private Client clientNew;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO findByEmail(String email) {
       return this.clientRepository.findByEmail(email).map(ClientDTO::new).orElse(null);
    }

    @Override
    public Set<ClientDTO> findAll() {
         return this.clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toSet());
    }

    @Override
    public ClientDTO finById(Long id)
    {
        return this.clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

    @Override
    public ResponseEntity<?> save(ClientDTO clientDTO) {
        log("inicio de operacion de creacion de cliente");
        clientNew = new Client();
//        clientDtoOld = new ClientDTO();
        this.response = new HashMap<>();

        try {
            if (this.clientRepository.findByEmail(clientDTO.getEmail()).isEmpty()) {
//                this.clientDtoNew = userMapper.userToUserDto(UserRepository.save(this.user));

                this.clientNew = this.clientRepository.save(this.homeBankingMapper.clientDtoToClient(clientDTO));
//                this.clientDtoNew = clientMapper.clientDtoToClient(clientRepository.save(clientDTO));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, clientNew);
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
        log("fin de operacion de cracion de cliente ");
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public ResponseEntity<?> update(ClientDTO clientDTO) {
        clientDtoOld = new ClientDTO();
        clientNew = new Client();

        this.response = new HashMap<>();

        try {
            clientDTO = findByEmail(clientDTO.getEmail());
            if (clientDTO == null) {
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            } else {
                clientDtoOld.setNames(clientDTO.getNames());
                clientDtoOld.setLastName(clientDTO.getLastName());
                clientDtoOld.setMotherLastName(clientDTO.getMotherLastName());
                clientDtoOld.setRuth(clientDTO.getRuth());
                clientDtoOld.setBirthDate(clientDTO.getBirthDate());
                clientDtoOld.setTelephoneNumber(clientDTO.getTelephoneNumber());
                clientDtoOld.setEmail(clientDTO.getEmail());
                clientDtoOld.setTotalLimit(clientDTO.getTotalLimit());
                clientDtoOld.setDebtAccount(clientDTO.getDebtAccount());
                clientDtoOld.setAvailableSpace(clientDTO.getAvailableSpace());

                clientNew = this.clientRepository.save(this.homeBankingMapper.clientDtoToClient(clientDtoOld));
                               // clientDtoNew = this.clientRepository.findByEmail(clientDTO.getEmail()).map(ClientDTO::new).orElse(null);
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, clientNew);
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
    public ResponseEntity<?> delete(ClientDTO clientDTO) {
        clientDtoOld = new ClientDTO();

        this.response = new HashMap<>();
        try {
            clientDtoOld = findByEmail(clientDTO.getEmail());
            if (clientDtoOld == null) {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.NOT_FOUND;
            } else {
//                clientDtoOld.setEnabled(false);
                this.clientRepository.save(this.homeBankingMapper.clientDtoToClient(clientDtoOld));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.http = HttpStatus.ACCEPTED;
            }
        } catch (Exception er) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK + er.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

}
