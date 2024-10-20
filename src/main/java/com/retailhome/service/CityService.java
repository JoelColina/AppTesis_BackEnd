package com.retailhome.service;

import com.retailhome.dtos.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> findAll();

    CityDTO findById(Long id);

}
