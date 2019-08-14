package com.bankingsystem.clientstransactions.repository;

import com.bankingsystem.clientstransactions.entities.AccountTransaction;
import com.bankingsystem.clientstransactions.entities.enums.TRANSACTION_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransaction, BigInteger> {

    @Query(value="select accountTransaction from AccountTransaction accountTransaction where accountTransaction.crebitAccountId.id=accountId or accountTransaction.debitAccountId.id=accountId")
    List<AccountTransaction> findByAccountId(@Param(value="accountId") BigInteger accountId);

    @Query(value="select accountTransaction from AccountTransaction accountTransaction where accountTransaction.status=:status")
    List<AccountTransaction> findAllByStatus(@Param(value="status") TRANSACTION_STATUS status);



}
