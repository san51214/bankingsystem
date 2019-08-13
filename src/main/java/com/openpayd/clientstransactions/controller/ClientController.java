package com.openpayd.clientstransactions.controller;

import com.openpayd.clientstransactions.exceptionhandler.ErrorCode;
import com.openpayd.clientstransactions.exceptionhandler.OpenPaydException;
import com.openpayd.clientstransactions.model.Client;
import com.openpayd.clientstransactions.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map> getClient(@RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname,
                                         @RequestParam(value = "detailed",required = false,defaultValue = "false") boolean detailed) {

        return new ResponseEntity<>(clientService.findByNameSurname(name, surname,detailed), HttpStatus.OK);
    }

    @GetMapping( produces = "application/json")
    public ResponseEntity<Map> getAllClients(@RequestParam(value = "detailed",required = false, defaultValue = "false") boolean detailed) {

        return new ResponseEntity<>(clientService.findAllClients(detailed), HttpStatus.OK);
    }

    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<Map> getAllClientById(@PathVariable(value="id")BigInteger id,@RequestParam(value = "detailed",required = false,defaultValue = "false") boolean detailed) {

        return new ResponseEntity<>(clientService.findById(id,detailed), HttpStatus.OK);
    }


}
