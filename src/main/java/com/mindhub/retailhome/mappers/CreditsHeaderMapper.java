package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.CreditsHeaderDTO;
import com.mindhub.retailhome.models.CreditsHeader;

public interface CreditsHeaderMapper {

    CreditsHeader creditsHeaderDtoToCreditsHeader (CreditsHeaderDTO creditsHeaderDTO);

    CreditsHeaderDTO creditsHeaderToCreditsHeaderDto (CreditsHeader creditsHeader);
}
