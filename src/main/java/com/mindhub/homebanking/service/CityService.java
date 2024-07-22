package com.mindhub.homebanking.service;

import com.mindhub.homebanking.dtos.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> findAll();

    CityDTO findById(long id);

}
