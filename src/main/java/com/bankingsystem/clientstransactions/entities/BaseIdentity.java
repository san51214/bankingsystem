package com.bankingsystem.clientstransactions.entities;

import com.bankingsystem.clientstransactions.utils.DateTimeUtil;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


@MappedSuperclass

public class BaseIdentity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="record_entry_sequence")
    @SequenceGenerator(name="record_entry_sequence",sequenceName = "record_entry_sequence",schema = "client_transactions", catalog = "postgres",allocationSize = 1, initialValue = 1000)

    @Id
    @Column(name = "id")
    private BigInteger id;

    @Basic
    @Column(name = "date_created")
    private Date dateCreated;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated==null?DateTimeUtil.getNow(): dateCreated;
    }
}
