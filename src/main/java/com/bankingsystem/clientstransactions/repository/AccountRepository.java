package com.bankingsystem.clientstransactions.repository;

import com.bankingsystem.clientstransactions.entities.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<ClientAccount, BigInteger> {
    @Query(value="select clientAccount from ClientAccount  clientAccount where clientAccount.clientId.id =:clientId")
    List<ClientAccount> getByClientId(@Param(value = "clientId") BigInteger clientId);
}
