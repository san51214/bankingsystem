package com.openpayd.clientstransactions.controller;

import com.openpayd.clientstransactions.exceptionhandler.ErrorCode;
import com.openpayd.clientstransactions.exceptionhandler.OpenPaydException;
import com.openpayd.clientstransactions.model.Client;
import com.openpayd.clientstransactions.service.ClientService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "client")
public class ClientController {

    final
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {

        try {

            return new ResponseEntity<Client>(clientService.create(client), HttpStatus.OK);

        } catch (Exception ex) {

            throw new OpenPaydException(ex.getMessage(), ErrorCode.ALREADY_EXIST);
        }
    }


    @GetMapping(value = "/byNameSurname", produces = "application/json")
    public ResponseEntity<Client> getClient(@RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname) {

        return new ResponseEntity<Client>(clientService.findByNameSurname(name, surname), HttpStatus.OK);
    }

    @GetMapping( produces = "application/json")
    public ResponseEntity<List<Client>> getAllClients() {

        return new ResponseEntity<>(clientService.findAllClients(), HttpStatus.OK);
    }


}
