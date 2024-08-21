package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.ClientDTO;
import com.mindhub.retailhome.mappers.ClientMapper;
import com.mindhub.retailhome.models.Client;
import com.mindhub.retailhome.repositories.ClientRepository;
import com.mindhub.retailhome.service.ClientService;
import com.mindhub.retailhome.utils.Constants;
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
    private ClientDTO clientDtoOld;
    private ClientDTO clientDtoNew;
    private HttpStatus http;
    private Client clientNew;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
        clientNew = new Client();
//        clientDtoOld = new ClientDTO();
        this.response = new HashMap<>();

        try {
            if (this.clientRepository.findByEmail(clientDTO.getEmail()) == null) {
//                this.clientDtoNew = userMapper.userToUserDto(UserRepository.save(this.user));

                this.clientNew = this.clientRepository.save(this.clientMapper.clientDtoToClient(clientDTO));
//                this.clientDtoNew = clientMapper.clientDtoToClient(clientRepository.save(clientDTO));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, clientNew);
                this.http = HttpStatus.CREATED;
            } else {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }
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
        clientNew = null;

        this.response = new HashMap<>();

        try {
            clientDTO = findByEmail(clientDTO.getEmail());
            if (clientDTO == null) {
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            } else {
                clientDtoNew.setNames(clientDTO.getNames());
                clientDtoNew.setLastName(clientDTO.getLastName());
                clientDtoNew.setMotherLastName(clientDTO.getMotherLastName());
                clientDtoNew.setRuth(clientDTO.getRuth());
                clientDtoNew.setBirthDate(clientDTO.getBirthDate());
                clientDtoNew.setTelephoneNumber(clientDTO.getTelephoneNumber());
                clientDtoNew.setEmail(clientDTO.getEmail());
                clientDtoNew.setTotalLimit(clientDTO.getTotalLimit());
                clientDtoNew.setDebtAccount(clientDTO.getDebtAccount());
                clientDtoNew.setAvailableSpace(clientDTO.getAvailableSpace());

                clientNew = this.clientRepository.save(this.clientMapper.clientDtoToClient(clientDtoNew));
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
