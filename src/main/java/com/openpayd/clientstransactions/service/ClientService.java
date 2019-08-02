package com.openpayd.clientstransactions.service;

import com.openpayd.clientstransactions.entities.ClientAddress;
import com.openpayd.clientstransactions.exceptionhandler.ErrorCode;
import com.openpayd.clientstransactions.exceptionhandler.OpenPaydException;
import com.openpayd.clientstransactions.model.Client;
import com.openpayd.clientstransactions.repository.ClientAddressRepository;
import com.openpayd.clientstransactions.repository.ClientRepository;
import com.openpayd.clientstransactions.utils.ModelTransformarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientAddressRepository clientAddressRepository;

    @Transactional
    public Client create(Client client) {

        com.openpayd.clientstransactions.entities.Client clientEntity = ModelTransformarUtil.toClientEntity(client);
        Set<ClientAddress> addressEntities = ModelTransformarUtil.toClientAddressEntities(client);
        client.setId(null);
        com.openpayd.clientstransactions.entities.Client savedClientEntity = clientRepository.save(clientEntity);

        addressEntities.forEach(ae -> ae.setClientByClientId(clientEntity));
        Set<ClientAddress> savedAddressEntities = new HashSet<>(clientAddressRepository.saveAll(addressEntities));

        return ModelTransformarUtil.toClientModel(savedClientEntity, savedAddressEntities);

    }

    public Client findByNameSurname(String name, String surname) {
        com.openpayd.clientstransactions.entities.Client client = clientRepository.findByNameSurname(name, surname);

        if (client == null) {
            throw new OpenPaydException("No result found", ErrorCode.NOT_FOUND);
        }

        Set<ClientAddress> clientAddressList = clientAddressRepository.getByClient(client);

        return ModelTransformarUtil.toClientModel(client, clientAddressList);
    }

    public List<Client> findAllClients()
    {
       List<com.openpayd.clientstransactions.entities.Client> allClients = new ArrayList<>();
       clientRepository.findAll().forEach(c->allClients.add(c));

       Set<ClientAddress> allClientsAddresses = clientAddressRepository.getByClients(allClients);

       List<Client> allClientsModel = new ArrayList<>();

       allClients.forEach(c-> {

           allClientsModel.add( ModelTransformarUtil.toClientModel(c,  allClientsAddresses.stream().filter(a->a.getClientByClientId().equals(c)).collect(Collectors.toSet())) );

       });

       return allClientsModel;
    }


}

