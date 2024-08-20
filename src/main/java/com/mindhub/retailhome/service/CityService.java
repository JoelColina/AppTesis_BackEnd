package com.mindhub.retailhome.service;

import com.mindhub.retailhome.dtos.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> findAll();

    CityDTO findById(Long id);

}
