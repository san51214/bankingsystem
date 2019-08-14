package com.bankingsystem.clientstransactions.service;

import com.bankingsystem.clientstransactions.entities.enums.TRANSACTION_STATUS;
import com.bankingsystem.clientstransactions.entities.AccountTransaction;
import com.bankingsystem.clientstransactions.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountTransactionsRepository accountTransactionsRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public AccountTransaction save(AccountTransaction accountTransaction, TRANSACTION_STATUS transaction_status)
    {
        accountTransaction.setStatus(transaction_status);
        return accountTransactionsRepository.save(accountTransaction);
    }

    public AccountTransaction findById(BigInteger id)
    {
        return accountTransactionsRepository.findById(id).get();
    }

    public List<AccountTransaction> findByAccountId(BigInteger accountId)
    {
        return accountTransactionsRepository.findByAccountId(accountId);
    }

    public List<AccountTransaction> getAllPendingTransactions()
    {
        return accountTransactionsRepository.findAllByStatus(TRANSACTION_STATUS.PENDING);
    }

    public AccountTransaction processTransaction(AccountTransaction accountTransaction)
    {
        return accountTransactionsRepository.save(accountTransaction);
    }

}
