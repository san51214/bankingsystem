package com.bankingsystem.clientstransactions.service;


import com.bankingsystem.clientstransactions.entities.ClientAccount;
import com.bankingsystem.clientstransactions.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Transactional
    public ClientAccount createAccount(ClientAccount clientAccount){

        return accountRepository.save(clientAccount);
    }

    public ClientAccount getById(BigInteger id)
    {
        return accountRepository.findById(id).get();
    }

    public List<ClientAccount> getByClientId(BigInteger clientId)
    {
        return accountRepository.getByClientId(clientId);
    }
    @Transactional
    public ClientAccount updateClientAccount(ClientAccount clientAccount)
    {
        return accountRepository.save(clientAccount);
    }

}
