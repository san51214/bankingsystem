package com.bankingsystem.clientstransactions.repository;

import com.bankingsystem.clientstransactions.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ClientRepository extends CrudRepository<Client, BigInteger> {
    @Query(value = "select client from Client client where client.name=:name and client.surname=:surname")
    Client findByNameSurname(@Param(value = "name")String name, @Param(value = "surname")String surname );

}
