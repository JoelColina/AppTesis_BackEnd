package com.mindhub.retailhome.service.implentacion;

import com.mindhub.retailhome.dtos.AddressesDTO;
import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.mappers.CreditsHeaderMapper;
import com.mindhub.retailhome.models.Addresses;
import com.mindhub.retailhome.models.CreditsHeader;
import com.mindhub.retailhome.repositories.CreditsHeaderRepository;
import com.mindhub.retailhome.service.CreditsHeaderService;
import com.mindhub.retailhome.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    public List<CreditsHeaderDTO> findCreditsHeaderByClient(String idClient) {
        return this.creditsHeaderRepository.findCreditsHeaderByClient(idClient).stream().map(CreditsHeaderDTO::new).collect(Collectors.toList());
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

        List<CreditsHeaderDTO> listDtoNew = findCreditsHeaderByClient(creditsHeaderDTO.getIdClient());

        try {
            if (listDtoNew.isEmpty()){
                operation = false;
            }else{
                listDtoNew = listDtoNew.stream().filter(x -> x.equals(creditsHeaderDTO)).toList();
                CreditsHeaderDTO deleteDto = getCreditsHeaderDTO(creditsHeaderDTO, (CreditsHeader) listDtoNew);

                deleteDto.setEnabled(false);
                update(deleteDto);
                operation = true;
            }
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
            List<CreditsHeaderDTO> listDtoNew = findCreditsHeaderByClient(creditsHeaderDTO.getIdClient());

            if (listDtoNew.isEmpty()){
                this.response.put(Constants.GEMERAL.ERROR, Constants.OPERATIONS.OPERATION_NOT_OK);
                this.http = HttpStatus.CONFLICT;
            }else {

                listDtoNew = listDtoNew.stream().filter(x -> x.equals(creditsHeaderDTO)).toList();

                CreditsHeaderDTO creditsHeaderDtoOld = getCreditsHeaderDTO(creditsHeaderDTO, (CreditsHeader) listDtoNew);

                this.creditsHeaderNew = this.creditsHeaderRepository.save(this.creditsHeaderMapper.creditsHeaderDtoToCreditsHeader(creditsHeaderDtoOld));
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

    private CreditsHeaderDTO getCreditsHeaderDTO(CreditsHeaderDTO creditsHeaderDTO, CreditsHeader listDtoNew) {

        CreditsHeaderDTO creditsHeaderDTONew = new CreditsHeaderDTO(listDtoNew);

        creditsHeaderDTONew.setRequestedAmount(creditsHeaderDTO.getRequestedAmount());
        creditsHeaderDTONew.setQuotaNumber(creditsHeaderDTO.getQuotaNumber());

        return creditsHeaderDTONew;

    }
}
