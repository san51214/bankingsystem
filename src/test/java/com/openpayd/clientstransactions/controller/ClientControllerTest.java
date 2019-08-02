package com.openpayd.clientstransactions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openpayd.clientstransactions.entities.ADDRESS_TYPE;
import com.openpayd.clientstransactions.model.Client;
import com.openpayd.clientstransactions.model.ClientAddress;
import com.openpayd.clientstransactions.service.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;


    @Test
    public void testCreateClient() throws JsonProcessingException {

        ClientAddress primaryAddess = new ClientAddress(BigInteger.ZERO, ADDRESS_TYPE.PRIMARY, BigInteger.ZERO,"first line","2nd line", "London", "UK");
        ClientAddress secondaryAddess = new ClientAddress(BigInteger.ZERO, ADDRESS_TYPE.SECONDARY,BigInteger.ZERO,"first line","2nd line", "Manchester", "UK");

        Set<ClientAddress> clientAddresses = new HashSet<>();
        clientAddresses.add(primaryAddess);
        clientAddresses.add(secondaryAddess);


        Client client = new Client(BigInteger.ZERO,"Abc","Xyz",clientAddresses);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(client);


        Assert.assertNotNull(json);
        //when(clientService.create()).thenReturn()


    }
}