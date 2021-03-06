package com.bankingsystem.clientstransactions.entities;

import com.bankingsystem.clientstransactions.entities.enums.TRANSACTION_STATUS;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
@Data
@Entity
@Table(name = "account_transaction", schema = "client_transactions", catalog = "postgres")
public class AccountTransaction extends BaseIdentity{

    @Basic
    @Column(name = "amount")
    private Double amount;
    @Basic
    @Column(name = "message")
    private String message;

    @Basic
    @Column(name = "status")
    private TRANSACTION_STATUS status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id", nullable = false)
    private ClientAccount debitAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crebit_account_id", referencedColumnName = "id", nullable = false)
    private ClientAccount crebitAccountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(message, that.message) &&
                Objects.equals(getDateCreated(), that.getDateCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), amount, message, getDateCreated());
    }
}
