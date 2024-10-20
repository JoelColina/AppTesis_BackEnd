package com.retailhome.mappers;

import com.retailhome.dtos.TransactionDTO;
import com.retailhome.dtos.TransactionDTO;
import com.retailhome.models.Transaction;
import com.retailhome.models.Transaction;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface TransactionMapper {
    Transaction toTransaction (TransactionDTO transactionDTO);

    TransactionDTO toTransactionDTO (Optional<Transaction> transaction);
    
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
        return transactionList.stream().map(this::toTransactionDTO).collect(Collectors.toList());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Transaction updateTransactionFromTransactionDTO(TransactionDTO transactionlDTO, @MappingTarget Transaction transaction);
}
