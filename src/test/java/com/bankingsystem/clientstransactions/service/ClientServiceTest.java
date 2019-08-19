package com.bankingsystem.clientstransactions.service;

import com.bankingsystem.clientstransactions.entities.enums.ADDRESS_TYPE;
import com.bankingsystem.clientstransactions.model.Client;
import com.bankingsystem.clientstransactions.model.ClientAddress;
import com.bankingsystem.clientstransactions.repository.ClientAddressRepository;
import com.bankingsystem.clientstransactions.repository.ClientRepository;
import com.bankingsystem.clientstransactions.utils.ModelTransformarUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ClientAddressRepository clientAddressRepository;

    @Autowired
    private ClientService clientService;

    @Test
    public void testCreateClient() throws JsonProcessingException {

        ClientAddress primaryAddess = new ClientAddress(BigInteger.ZERO, ADDRESS_TYPE.PRIMARY, BigInteger.ZERO,"first line","2nd line", "London", "UK");
        ClientAddress secondaryAddess = new ClientAddress(BigInteger.ZERO, ADDRESS_TYPE.SECONDARY,BigInteger.ZERO,"first line","2nd line", "Manchester", "UK");

        Set<ClientAddress> clientAddresses = new HashSet<>();
        clientAddresses.add(primaryAddess);
        clientAddresses.add(secondaryAddess);

        Client client = new Client(BigInteger.ZERO,"Abc","Xyz",clientAddresses);

        com.bankingsystem.clientstransactions.entities.Client clientToSave =  ModelTransformarUtil.toClientEntity(client);
        com.bankingsystem.clientstransactions.entities.Client clientSaved =  ModelTransformarUtil.toClientEntity(client);
        clientSaved.setId(BigInteger.ONE);
        when(clientRepository.save(clientToSave)).thenReturn(clientSaved);

        Set<com.bankingsystem.clientstransactions.entities.ClientAddress> clientAddressesToSave = ModelTransformarUtil.toClientAddressEntities(client);
        Set<com.bankingsystem.clientstransactions.entities.ClientAddress> clientAddressesSaved = ModelTransformarUtil.toClientAddressEntities(client);

        when(clientAddressRepository.saveAll(any(HashSet.class))).thenAnswer(new Answer<List<com.bankingsystem.clientstransactions.entities.ClientAddress>>()
        {
            @Override
            public List<com.bankingsystem.clientstransactions.entities.ClientAddress> answer(InvocationOnMock invocationOnMock) throws Throwable {

                int i=5;
                for(com.bankingsystem.clientstransactions.entities.ClientAddress clientAddress: clientAddressesSaved)
                {
                    clientAddress.setId( new BigInteger(String.valueOf(i++)) );
                }
                List savedAddressList = new ArrayList<>(clientAddressesSaved);

                return savedAddressList;
            }
        });


        Client createdClient =  clientService.create(client);

        Assert.assertNotNull(createdClient);

        Comparator<ClientAddress> comparator = new Comparator<ClientAddress>() {
            @Override
            public int compare(ClientAddress o1, ClientAddress o2) {
                return o1.getId().intValue()>o2.getId().intValue()?o1.getId().intValue():o2.getId().intValue();
            }
        };
        List<ClientAddress> clientAddressesSaved1 = new ArrayList<>(createdClient.getClientAddresses());
        Collections.sort( clientAddressesSaved1, comparator);

        Assert.assertEquals(clientAddressesSaved1.size(),2);
        Assert.assertEquals(clientAddressesSaved1.get(0).getId().intValue(),5);
        Assert.assertEquals(clientAddressesSaved1.get(1).getId().intValue(),6);

    }


}