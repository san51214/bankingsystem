package com.bankingsystem.clientstransactions.service;

import com.bankingsystem.clientstransactions.exceptionhandler.BankingSystemException;
import com.bankingsystem.clientstransactions.model.Client;
import com.bankingsystem.clientstransactions.repository.ClientAddressRepository;
import com.bankingsystem.clientstransactions.repository.ClientRepository;
import com.bankingsystem.clientstransactions.utils.ModelTransformarUtil;
import com.bankingsystem.clientstransactions.entities.ClientAddress;
import com.bankingsystem.clientstransactions.exceptionhandler.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientAddressRepository clientAddressRepository;

    @Transactional
    public Client create(Client client) {

        com.bankingsystem.clientstransactions.entities.Client clientEntity = ModelTransformarUtil.toClientEntity(client);
        Set<ClientAddress> addressEntities = ModelTransformarUtil.toClientAddressEntities(client);
        client.setId(null);
        com.bankingsystem.clientstransactions.entities.Client savedClientEntity = clientRepository.save(clientEntity);

        addressEntities.forEach(ae -> ae.setClientByClientId(clientEntity));
        Set<ClientAddress> savedAddressEntities = new HashSet<>(clientAddressRepository.saveAll(addressEntities));

        return ModelTransformarUtil.toClientModel(savedClientEntity, savedAddressEntities);

    }

    public Map findByNameSurname(String name, String surname, boolean detailed)
    {
        final Map map = new HashMap();

        map.put("client", detailed?findByNameSurname( name, surname): clientRepository.findByNameSurname(name, surname));

        return map;

    }

    private Client findByNameSurname(String name, String surname) {
        com.bankingsystem.clientstransactions.entities.Client client = clientRepository.findByNameSurname(name, surname);

        if (client == null) {
            throw new BankingSystemException("No result found", ErrorCode.NOT_FOUND);
        }

        Set<ClientAddress> clientAddressList = clientAddressRepository.getByClient(client);

        return ModelTransformarUtil.toClientModel(client, clientAddressList);
    }

    public Map findAllClients(boolean detailed)
    {
        final Map map = new HashMap();

        map.put("clients", detailed ? findAllClients() : clientRepository.findAll());

        return map;
    }

    private List<Client> findAllClients()
    {
       List<com.bankingsystem.clientstransactions.entities.Client> allClients = new ArrayList<>();
       clientRepository.findAll().forEach(c->allClients.add(c));

       Set<ClientAddress> allClientsAddresses = clientAddressRepository.getByClients(allClients);

       List<Client> allClientsModel = new ArrayList<>();

       allClients.forEach(c-> {

           allClientsModel.add( ModelTransformarUtil.toClientModel(c,  allClientsAddresses.stream().filter(a->a.getClientByClientId().equals(c)).collect(Collectors.toSet())) );

       });

       return allClientsModel;
    }

    public Map findById(BigInteger id,boolean detailed)
    {
        final Map map = new HashMap();

        map.put("clients", detailed ? findById(id) : clientRepository.findById(id).get());

        return map;

    }

    private Client findById(BigInteger id)
    {
        com.bankingsystem.clientstransactions.entities.Client client = clientRepository.findById(id).get();
        Set<ClientAddress> clientAddresses = clientAddressRepository.getByClient(client);

        return ModelTransformarUtil.toClientModel(client,clientAddresses);
    }


}

