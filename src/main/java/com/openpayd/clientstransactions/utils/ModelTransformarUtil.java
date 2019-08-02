package com.openpayd.clientstransactions.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openpayd.clientstransactions.entities.ClientAddress;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelTransformarUtil {

    public static com.openpayd.clientstransactions.model.Client toClientModel(com.openpayd.clientstransactions.entities.Client client, Set<ClientAddress> clientAddresses)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        com.openpayd.clientstransactions.model.Client clientModel = objectMapper.convertValue(client, com.openpayd.clientstransactions.model.Client.class);
        Set<com.openpayd.clientstransactions.model.ClientAddress> addresses = new HashSet<>();
        clientAddresses.forEach(ca -> addresses.add(objectMapper.convertValue(ca, com.openpayd.clientstransactions.model.ClientAddress.class)));

        clientModel.setClientAddresses(addresses);

        return clientModel;
    }

    public static com.openpayd.clientstransactions.entities.Client toClientEntity(com.openpayd.clientstransactions.model.Client clientModel)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        com.openpayd.clientstransactions.entities.Client clientEntity = objectMapper.convertValue(clientModel, com.openpayd.clientstransactions.entities.Client.class);

        return clientEntity;
    }

    public static Set<com.openpayd.clientstransactions.entities.ClientAddress> toClientAddressEntities(com.openpayd.clientstransactions.model.Client clientModel)
    {
        Set<com.openpayd.clientstransactions.model.ClientAddress> clientAddresses = clientModel.getClientAddresses();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Set<com.openpayd.clientstransactions.entities.ClientAddress> addresses = new HashSet<>();
        clientAddresses.forEach(ca -> {
            ClientAddress clientAddress = objectMapper.convertValue(ca, com.openpayd.clientstransactions.entities.ClientAddress.class);

            com.openpayd.clientstransactions.entities.Client clientEntity = new com.openpayd.clientstransactions.entities.Client();
            clientEntity.setId(clientModel.getId());
            clientAddress.setClientByClientId(clientEntity);

            addresses.add(clientAddress);
        });

        return addresses;
    }

}
