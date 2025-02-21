package com.bo.orkaw.models.accounts;

import com.bo.orkaw.models.MasterUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "master_account")
public class MasterAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @JsonIgnoreProperties("accounts") // Ignore only the accounts field in MasterUser to prevent recursion
  //@JsonBackReference  // Prevents infinite recursion Tells Jackson not to serialize back-references
  private MasterUser user;

  @Column(name = "balance", nullable = false)
  private Double balance;

}
