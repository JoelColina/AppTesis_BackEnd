package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.TransactionDTO;
import com.mindhub.retailhome.models.Transaction;

import java.util.Optional;

public interface TransactionMapper {
    Transaction transactionDtoToTransaction (TransactionDTO transactionDTO);

    TransactionDTO transactionToTransactionDto (Optional<Transaction> transaction);

}
