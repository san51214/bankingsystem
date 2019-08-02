package com.openpayd.clientstransactions.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "client_address", schema = "client_transactions", catalog = "postgres")
@Data
public class ClientAddress extends BaseIdentity{

    @Basic
    @Column(name = "address_type")
    private ADDRESS_TYPE addressType;
    @Basic
    @Column(name = "line1")
    private String line1;
    @Basic
    @Column(name = "line2")
    private String line2;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "country")
    private String country;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client clientByClientId;

    public ClientAddress() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientAddress that = (ClientAddress) o;
        return addressType == that.addressType &&
                clientByClientId.equals(that.clientByClientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addressType, clientByClientId);
    }
}
