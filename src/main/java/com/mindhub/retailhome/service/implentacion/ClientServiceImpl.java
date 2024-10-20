package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.mappers.ClientMapper;
import com.mindhub.retailhome.models.Client;
import com.mindhub.retailhome.repositories.ClientRepository;
import com.mindhub.retailhome.service.ClientService;
import com.mindhub.retailhome.utils.Constants;
import com.mindhub.retailhome.utils.UsernameRandom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientMapper clientMapper;
    private ClientRepository clientRepository;

    private Map<String, Object> response;
    private Client clientNew;
    private ClientDTO clientDtoNew;
    private ClientDTO clientDtoOld;
    private HttpStatus http;
    private long userId;

    public ClientServiceImpl(ClientRepository clientRepository,
                             UsernameRandom usernameRandom,
                             ClientDTO clientDtoNew,
                             ClientDTO clientDtoOld,
                             ClientMapper clientMapper
    ) {}

 //   @Override
//    public ClientDTO findByEmail(String email) {
//        return this.clientRepository.findByEmail(email);
//    }

//    @Override
//    public Set<ClientDTO> findAll() {
//         return this.clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toSet());
//    }

//    @Override
//    public ClientDTO finById(Long id)
//    {
//        return this.clientRepository.findById(id).map(ClientDTO::new).orElse(null);
//    }

    @Override
    public ClientDTO findByEmail(String email) {
        return null;
    }

    @Override
    public Set<ClientDTO> findAll() {
        return Set.of();
    }

    @Override
    public ClientDTO finById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> save(ClientDTO clientDTO) {

        clientNew = new Client();
        this.response = new HashMap<>();
        this.userId = 0;

        try {

            if(this.clientRepository.findByEmail(clientDTO.getEmail()) != null){
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
                return new ResponseEntity<>(this.response, this.http);
            }

            while (userId==0) {
                userId = UsernameRandom.userNameRandom(Constants.USER_GENERATE);
                clientNew = this.clientRepository.findById(userId);
                if (clientNew != null) {
                    userId = 0;
                }
            }

            clientDTO.setIdClient(userId);
            this.clientNew = this.clientRepository.save(this.clientMapper.clientDtoToClient(clientDTO));
            this.clientDtoNew = clientMapper.clientToClientDto(clientRepository.save(clientNew));

            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, clientDtoNew);
            this.http = HttpStatus.CREATED;

        } catch (Exception ex) {
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, ex.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public ResponseEntity<?> update(ClientDTO clientDTO) {
        clientDtoNew = null;
        Client clientOld = null;
        clientNew = null;

        this.response = new HashMap<>();

        try {
            clientDTO = findByEmail(clientDTO.getEmail());
            if (clientDTO == null) {
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            } else {
                clientOld.setNames(clientDTO.getNames());
                clientOld.setLastName(clientDTO.getLastName());
                clientOld.setMotherLastName(clientDTO.getMotherLastName());
                clientOld.setRuth(clientDTO.getRuth());
                clientOld.setBirthDate(clientDTO.getBirthDate());
                clientOld.setTelephoneNumber(clientDTO.getTelephoneNumber());
                clientOld.setEmail(clientDTO.getEmail());
                clientOld.setTotalLimit(clientDTO.getTotalLimit());
                clientOld.setDebtAccount(clientDTO.getDebtAccount());
                clientOld.setAvailableSpace(clientDTO.getAvailableSpace());
                clientOld.setIdClient(clientDTO.getIdClient());
                clientOld.setEnabled(clientDTO.isEnabled());

                clientNew = this.clientRepository.save(this.clientMapper.clientDtoToClient(clientDtoOld));
                this.clientDtoNew = clientMapper.clientToClientDto(clientRepository.save(clientNew));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, clientDtoNew);
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
                clientDtoOld.setEnabled(false);
                this.clientRepository.save(this.clientMapper.clientDtoToClient(clientDtoOld));
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
