package com.openpayd.clientstransactions.controller;

import com.openpayd.clientstransactions.entities.ClientAccount;
import com.openpayd.clientstransactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClientAccount> createAccount(@RequestBody ClientAccount clientAccount)
    {
      return new ResponseEntity<> (accountService.createAccount(clientAccount), HttpStatus.OK);
    }

    @GetMapping(value="/{id}",produces = "application/json")
    public ResponseEntity<ClientAccount> getClientAccountById(@PathVariable(value="id")BigInteger id)
    {
        return new ResponseEntity<> (accountService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value="/byClientId/{id}",produces = "application/json")
    public ResponseEntity<List<ClientAccount>> getByClientId(@PathVariable(value="id")BigInteger clientId)
    {
        return new ResponseEntity<> (accountService.getByClientId(clientId), HttpStatus.OK);
    }

}
