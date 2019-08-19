package com.bankingsystem.clientstransactions.service;

import com.bankingsystem.clientstransactions.entities.enums.ACCOUNT_TYPE;
import com.bankingsystem.clientstransactions.entities.enums.BALANCE_STATUS;
import com.bankingsystem.clientstransactions.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.bankingsystem.clientstransactions.entities.Client;
import com.bankingsystem.clientstransactions.entities.ClientAccount;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Date;

import static org.junit.Assert.*;

public class AccountServiceTest {

    @Test
    public void createAccount() throws JsonProcessingException {

        Client client = new Client();
        client.setId(BigInteger.TEN);

        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setBalance(100d);
        clientAccount.setClientId(client);
        clientAccount.setDateCreated(new Date());
        clientAccount.setType(ACCOUNT_TYPE.CURRENT);
clientAccount.setStatus(BALANCE_STATUS.DEBIT);
        String json = CommonUtils.getObjectMapper(true).writeValueAsString(clientAccount);

        assertEquals(json==null, false);
    }
}