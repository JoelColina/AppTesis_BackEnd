package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.AccountDTO;
import com.mindhub.retailhome.models.Account;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper (unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AccountMapper {
    Account toAccount (AccountDTO accountDTO);

    AccountDTO toAccountDto (Account account);

    default List<AccountDTO> toDoList (List<Account> accountList){
        if (accountList == null) {
            return new ArrayList<>();
        }
        return accountList.stream().map(this::toAccountDto).collect(Collectors.toList());
    }

    default List<Account> toEntityList (List<AccountDTO> accountDTOList){
        if (accountDTOList == null) {
            return new ArrayList<>();
        }
        return accountDTOList.stream().map(this::toAccount).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Account updateAccountFromAccountDto(AccountDTO accountDto, @MappingTarget Account account);

}
