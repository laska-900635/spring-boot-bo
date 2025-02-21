package com.bo.orkaw.controllers;

import com.bo.orkaw.models.ApiResponse;
import com.bo.orkaw.models.accounts.MasterAccount;
import com.bo.orkaw.models.accounts.MasterAccountReqModel;
import com.bo.orkaw.services.MasterAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/master-account")
public class MasterAccountController {

  @Autowired
  private MasterAccountService masterAccountService;

  @GetMapping("list")
  public List<MasterAccount> getAllAccounts() {
    return masterAccountService.getAllAccounts();
  }
  @GetMapping("detail/{id}")
  public ApiResponse<Object> getTotalBalanceUser(@PathVariable(value = "id") UUID id) {
    return masterAccountService.getTotalBalanceUser(id);
  }

  @PostMapping("add")
  public ApiResponse<Object> addAccount(@RequestBody MasterAccountReqModel account) {
    return masterAccountService.addAccount(account);
  }
}
