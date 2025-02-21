package com.bo.orkaw.repositories;

import com.bo.orkaw.models.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MasterUserRepository extends JpaRepository<MasterUser, UUID> {
}
