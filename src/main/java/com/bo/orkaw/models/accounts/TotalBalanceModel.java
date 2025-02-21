package com.bo.orkaw.models.accounts;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TotalBalanceModel {
  private UUID userId;
  private String fullName;
  private double balance;
}
