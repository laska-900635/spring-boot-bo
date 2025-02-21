package com.bo.orkaw.repositories;

import com.bo.orkaw.models.accounts.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MasterAccountRepository extends JpaRepository<MasterAccount, UUID> {

  @Query("SELECT COALESCE(SUM(a.balance), 0) FROM MasterAccount a WHERE a.user.id = :userId")
  Double getTotalBalanceByUserId(UUID userId);
}
