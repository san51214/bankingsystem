package com.openpayd.clientstransactions.entities;

import com.openpayd.clientstransactions.utils.DateTimeUtil;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;


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
