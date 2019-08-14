package com.bankingsystem.clientstransactions.service;

import com.bankingsystem.clientstransactions.entities.AccountTransaction;
import com.bankingsystem.clientstransactions.entities.enums.TRANSACTION_STATUS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionProcessScheduler {

    @Autowired
    TransactionService transactionService;

    private static final Logger log;

    static {
        log = LoggerFactory.getLogger(TransactionProcessScheduler.class);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {

        List<AccountTransaction> allPendingTransactions =  transactionService.getAllPendingTransactions();
        log.info("Transaction processing started now-----", dateFormat.format(new Date()));

        allPendingTransactions.forEach(t-> {
            t.setStatus(TRANSACTION_STATUS.PROCESSED);
            transactionService.processTransaction(t);
        });

    }

}
