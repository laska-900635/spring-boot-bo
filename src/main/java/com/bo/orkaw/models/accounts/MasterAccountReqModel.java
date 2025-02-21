package com.bo.orkaw.models.accounts;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MasterAccountReqModel {

  private UUID userId;
  private Double balance;
}
