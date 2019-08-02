package com.openpayd.clientstransactions.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openpayd.clientstransactions.entities.ADDRESS_TYPE;
import com.openpayd.clientstransactions.entities.Client;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@Data
public class ClientAddress {

    private BigInteger id;
    private ADDRESS_TYPE addressType;
    private String line1;
    private String line2;
    private String city;
    private String country;
    private Date dateCreated;
    @JsonIgnore
    private BigInteger clientId;
    public ClientAddress(BigInteger id, ADDRESS_TYPE addressType, BigInteger clientId,String line1, String line2, String city, String country) {
        this.id = id;
        this.addressType = addressType;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.country = country;
        this.clientId = clientId;
    }

    public ClientAddress() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAddress that = (ClientAddress) o;
        return addressType == that.addressType &&
                clientId.equals(that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressType, clientId);
    }
}
