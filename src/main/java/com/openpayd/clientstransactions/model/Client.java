package com.openpayd.clientstransactions.model;


import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class Client {

    private BigInteger id;
    private String name;
    private String surname;
    Set<ClientAddress> clientAddresses;
    private Date dateCreated;
    public Client() {
    }

    public Client(BigInteger id, String name, String surname, Set<ClientAddress> clientAddresses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.clientAddresses = clientAddresses;
    }
}
