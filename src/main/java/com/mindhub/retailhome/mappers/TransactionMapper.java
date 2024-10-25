package com.mindhub.retailhome.mappers;


import com.mindhub.retailhome.dtos.TransactionDTO;
import com.mindhub.retailhome.models.Transaction;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TransactionMapper {
    Transaction toTransaction (TransactionDTO transactionDTO);

    TransactionDTO toTransactionDto (Optional<Transaction> transaction);

    default List<Transaction> toEntityList(List<TransactionDTO> transactionDTOList){
        if (transactionDTOList == null){
            return  new ArrayList<>();
        }
        return transactionDTOList.stream().map(this::toTransaction).collect(Collectors.toList());
    }

    default List<TransactionDTO> toDtoList(List<Optional<Transaction>> transactionList){
        if (transactionList == null){
            return  new ArrayList<>();
        }
        return transactionList.stream().map(this::toTransactionDto).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Transaction updateTransactionFromTransactionDTO(TransactionDTO transactionlDTO, @MappingTarget Transaction transaction);

}
