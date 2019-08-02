package com.openpayd.clientstransactions.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "client", schema = "client_transactions", catalog = "postgres")
@Data
public class Client extends BaseIdentity{
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;

    public Client() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId()) &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, surname);
    }
}
