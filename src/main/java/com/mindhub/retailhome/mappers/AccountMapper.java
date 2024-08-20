package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.models.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    Account accountDtoToAccount (AccountDTO accountDTO);

    AccountDTO accountToAccountDto (Account account);
}
