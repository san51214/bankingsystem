package com.bankingsystem.clientstransactions.utils;

import com.bankingsystem.clientstransactions.model.Client;
import com.bankingsystem.clientstransactions.model.ClientAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

public class ModelTransformarUtil {

    public static Client toClientModel(com.bankingsystem.clientstransactions.entities.Client client, Set<com.bankingsystem.clientstransactions.entities.ClientAddress> clientAddresses)
    {
        ObjectMapper objectMapper = CommonUtils.getObjectMapper(false);
        Client clientModel = objectMapper.convertValue(client, Client.class);
        Set<ClientAddress> addresses = new HashSet<>();
        clientAddresses.forEach(ca -> addresses.add(objectMapper.convertValue(ca, ClientAddress.class)));

        clientModel.setClientAddresses(addresses);

        return clientModel;
    }

    public static com.bankingsystem.clientstransactions.entities.Client toClientEntity(Client clientModel)
    {
        ObjectMapper objectMapper = CommonUtils.getObjectMapper(false);
        com.bankingsystem.clientstransactions.entities.Client clientEntity = objectMapper.convertValue(clientModel, com.bankingsystem.clientstransactions.entities.Client.class);

        return clientEntity;
    }

    public static Set<com.bankingsystem.clientstransactions.entities.ClientAddress> toClientAddressEntities(Client clientModel)
    {
        Set<ClientAddress> clientAddresses = clientModel.getClientAddresses();
        ObjectMapper objectMapper = CommonUtils.getObjectMapper(false);
        Set<com.bankingsystem.clientstransactions.entities.ClientAddress> addresses = new HashSet<>();
        clientAddresses.forEach(ca -> {
            com.bankingsystem.clientstransactions.entities.ClientAddress clientAddress = objectMapper.convertValue(ca, com.bankingsystem.clientstransactions.entities.ClientAddress.class);

            com.bankingsystem.clientstransactions.entities.Client clientEntity = new com.bankingsystem.clientstransactions.entities.Client();
            clientEntity.setId(clientModel.getId());
            clientAddress.setClientByClientId(clientEntity);

            addresses.add(clientAddress);
        });

        return addresses;
    }

}
