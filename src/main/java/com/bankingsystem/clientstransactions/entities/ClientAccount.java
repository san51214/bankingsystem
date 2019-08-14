package com.bankingsystem.clientstransactions.entities;

import com.bankingsystem.clientstransactions.entities.enums.ACCOUNT_TYPE;
import com.bankingsystem.clientstransactions.entities.enums.BALANCE_STATUS;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
@Data
@Entity
@Table(name = "client_account", schema = "client_transactions", catalog = "postgres")
public class ClientAccount extends BaseIdentity{

    @Basic
    @Column(name = "type")
    private ACCOUNT_TYPE type;
    @Basic
    @Column(name = "status")
    private BALANCE_STATUS status;
    @Basic
    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client clientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAccount that = (ClientAccount) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(type, that.type) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(status, that.status) &&
                Objects.equals(getDateCreated(), that.getDateCreated());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), type, balance, status, getDateCreated());
    }

}
