package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.mappers.CreditsHeaderMapper;
import com.mindhub.retailhome.models.CreditsHeader;
import com.mindhub.retailhome.repositories.CreditsHeaderRepository;
import com.mindhub.retailhome.service.CreditsHeaderService;
import com.mindhub.retailhome.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditsHeaderServiceImpl implements CreditsHeaderService {
    private Map<String, Object> response;
    private HttpStatus http;
    private CreditsHeaderDTO creditsHeaderDTONew;
    private CreditsHeaderMapper creditsHeaderMapper;
    private CreditsHeader creditsHeaderNew;

    @Autowired
    private CreditsHeaderRepository creditsHeaderRepository;

    @Override
    public Set<CreditsHeaderDTO> finAll() {
        return this.creditsHeaderRepository.findAll().stream().map(CreditsHeaderDTO::new).collect(Collectors.toSet());
    }

    @Override
    public CreditsHeaderDTO findById(Long id) {
        return this.creditsHeaderRepository.findById(id).map(CreditsHeaderDTO::new).orElse(null);
    }
    @Override
    public CreditsHeaderDTO findCreditsHeaderDto(String idClient) {
        return (CreditsHeaderDTO) this.creditsHeaderRepository.findAccountDto(idClient).mapToObj(CreditsHeaderDTO::new).toList();
    }

    @Override
    public ResponseEntity<?> save(CreditsHeaderDTO creditsHeaderDTO) {
        this.response = new HashMap<>();
        creditsHeaderNew = null;
        creditsHeaderDTONew = null;

        try {
            this.creditsHeaderNew = this.creditsHeaderRepository.save(this.creditsHeaderMapper.creditsHeaderDtoToCreditsHeader(creditsHeaderDTO));
            this.creditsHeaderDTONew = creditsHeaderMapper.creditsHeaderToCreditsHeaderDto(creditsHeaderRepository.save(creditsHeaderNew));

            this.creditsHeaderNew.setEnabled(true);
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
            this.response.put(Constants.USER.USER, creditsHeaderDTONew);
            this.http = HttpStatus.CREATED;

        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            this.http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response, this.http);
    }

    @Override
    public boolean delete(CreditsHeaderDTO creditsHeaderDTO) {
        boolean operation = false;

        CreditsHeaderDTO creditsHeaderDTONew = findCreditsHeaderDto(creditsHeaderDTO.getIdClient());

        try {
            creditsHeaderDTONew.setEnabled(false);
            update(creditsHeaderDTONew);
            operation = true;

        }catch (Exception e){
            operation = false;
        }

        return operation;
    }

    @Override
    public ResponseEntity<?> update(CreditsHeaderDTO creditsHeaderDTO) {
        this.response = new HashMap<>();
        this.creditsHeaderDTONew = null;
        this.creditsHeaderNew = null;

        try {
            creditsHeaderDTO = findCreditsHeaderDto(creditsHeaderDTO.getIdClient());
            if (creditsHeaderDTO == null){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                creditsHeaderDTONew.setRequestedAmount(creditsHeaderDTO.getRequestedAmount());
                creditsHeaderDTONew.setQuotaNumber(creditsHeaderDTO.getQuotaNumber());

                this.creditsHeaderNew = this.creditsHeaderRepository.save(this.creditsHeaderMapper.creditsHeaderDtoToCreditsHeader(creditsHeaderDTONew));
                this.creditsHeaderDTONew = creditsHeaderMapper.creditsHeaderToCreditsHeaderDto(creditsHeaderRepository.save(creditsHeaderNew));

                this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_OK);
                this.response.put(Constants.USER.USER, creditsHeaderDTONew);
                http = HttpStatus.ACCEPTED;
            }
        }catch (Exception e){
            this.response.put(Constants.GEMERAL.MESSAGE, Constants.OPERATIONS.OPERATION_NOT_OK);
            this.response.put(Constants.GEMERAL.ERROR, e.getMessage());
            http = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(this.response,this.http);
    }
}
