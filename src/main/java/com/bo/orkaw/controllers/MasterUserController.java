package com.bo.orkaw.controllers;

import com.bo.orkaw.models.ApiResponse;
import com.bo.orkaw.models.MasterUser;
import com.bo.orkaw.services.MasterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/master-user")
public class MasterUserController {

  @Autowired
  private MasterUserService masterUserService;

  @GetMapping("list")
  public List<MasterUser> getAllUsers() {
    return masterUserService.getAllUsers();
  }

  @PostMapping("add")
  public MasterUser addUser(@RequestBody MasterUser user) {
    return masterUserService.addUser(user);
  }

  @PatchMapping("edit")
  public ApiResponse<Object> editUser(@RequestBody MasterUser user) {
    return masterUserService.editUser(user);
  }
}
