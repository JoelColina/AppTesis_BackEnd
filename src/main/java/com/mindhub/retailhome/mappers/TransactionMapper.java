package com.mindhub.retailhome.mappers;

import com.mindhub.retailhome.dtos.TransactionDTO;
import com.mindhub.retailhome.models.Transaction;

public interface TransactionMapper {
    Transaction transactionDtoToTransaction (TransactionDTO transactionDTO);

    TransactionDTO transactionToTransactionDto (Transaction transaction);

}
