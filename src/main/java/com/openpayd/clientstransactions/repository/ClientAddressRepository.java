package com.openpayd.clientstransactions.repository;

import com.openpayd.clientstransactions.entities.Client;
import com.openpayd.clientstransactions.entities.ClientAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Repository
public interface ClientAddressRepository extends JpaRepository<ClientAddress, BigInteger> {

    @Query(value = "select clientAddress from ClientAddress clientAddress where clientAddress.clientByClientId=:clientByClientId")
    Set<ClientAddress> getByClient(@Param(value = "clientByClientId") Client client);

    @Query(value = "select clientAddress from ClientAddress clientAddress where clientAddress.clientByClientId in (:clients)")
    Set<ClientAddress> getByClients(@Param(value = "clients") List<Client> clients);


}
