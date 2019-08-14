package com.bankingsystem.clientstransactions.controller;

import com.bankingsystem.clientstransactions.entities.enums.TRANSACTION_STATUS;
import com.bankingsystem.clientstransactions.service.TransactionService;
import com.bankingsystem.clientstransactions.entities.AccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(consumes = "Application/json", produces = "application/json")
    public ResponseEntity<AccountTransaction> save(@RequestBody AccountTransaction accountTransaction)
    {
        return new ResponseEntity<>(transactionService.save(accountTransaction, TRANSACTION_STATUS.PENDING), HttpStatus.OK);
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<AccountTransaction> getById(@PathVariable(value="id")BigInteger id)
    {
        return new ResponseEntity<>(transactionService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value="/byAccount/{id}", produces = "application/json")
    public ResponseEntity<List<AccountTransaction>> getByAccounId(@PathVariable(value="id")BigInteger id)
    {
        return new ResponseEntity<>(transactionService.findByAccountId(id), HttpStatus.OK);
    }
}
