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
    private UsernameRandom usernameRandom;

    private Map<String, Object> response;
    private ClientDTO clientDtoOld;
    private ClientDTO clientDtoNew;
    private HttpStatus http;
    private Client clientNew;
    private String userId;

    public ClientServiceImpl(ClientRepository clientRepository, UsernameRandom usernameRandom) {
        this.clientRepository = clientRepository;
        this.usernameRandom = usernameRandom;
    }

    @Override
    public ClientDTO findByEmail(String email) {
        return this.clientRepository.findByEmail(email);
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
        clientDtoNew = new ClientDTO();
        clientNew = new Client();
        this.response = new HashMap<>();
        this.userId = null;

        try {

            if(this.clientRepository.findByEmail(clientDTO.getEmail()) != null){
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
                return new ResponseEntity<>(this.response, this.http);
            }

            while (userId.isEmpty()) {
                userId = UsernameRandom.userNameRandom(Constants.USER_GENERATE);
                clientDtoNew = this.clientRepository.findByIdClient(userId);
                if (clientDtoNew != null) {
                    userId = "";
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
        clientDtoOld = null;
        clientNew = null;

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
                clientDtoOld.setIdClient(clientDTO.getIdClient());
                clientDtoOld.setEnabled(clientDTO.isEnabled());

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
