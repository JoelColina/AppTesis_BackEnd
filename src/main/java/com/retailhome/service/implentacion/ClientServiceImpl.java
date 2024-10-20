package com.retailhome.service.implentacion;

import com.retailhome.dtos.ClientDTO;
import com.retailhome.mappers.ClientMapper;
import com.retailhome.models.Client;
import com.retailhome.repositories.ClientRepository;
import com.retailhome.service.ClientService;
import com.retailhome.utils.Constants;
import com.retailhome.utils.UsernameRandom;
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
    private String vacio;
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
        clientNew = new Client();
        this.response = new HashMap<>();
        this.vacio.isEmpty();
        this.userId = null;
        String nomId = "RH";

        try {
            if (this.clientRepository.findByEmail(clientDTO.getEmail()) == null) {
                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            } else {
                //if comprobar si cliente no este creado empty
                this.vacio = String.valueOf(this.clientRepository.findByEmail(clientDTO.getEmail()));

                if (vacio.isEmpty()){
                    userId = UsernameRandom.userNameRandom(nomId);
                    Client creCli = clientRepository.findById(userId);
                }

                this.clientNew = this.clientRepository.save(this.clientMapper.clientDtoToClient(clientDTO));
//                this.clientDtoNew = clientMapper.clientDtoToClient(clientRepository.save(clientDTO));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, clientNew);
                this.http = HttpStatus.CREATED;
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
