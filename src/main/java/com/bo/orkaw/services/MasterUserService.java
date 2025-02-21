package com.bo.orkaw.services;

import com.bo.orkaw.models.ApiResponse;
import com.bo.orkaw.models.MasterUser;
import com.bo.orkaw.repositories.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MasterUserService {

  @Autowired
  private MasterUserRepository masterUserRepository;

  public List<MasterUser> getAllUsers() {
    return masterUserRepository.findAll();
  }

  public Optional<MasterUser> getDetailUser(UUID id) {
    return masterUserRepository.findById(id);
  }

  public MasterUser addUser(MasterUser user) {
    return masterUserRepository.save(user);
  }

  public ApiResponse<Object> editUser(MasterUser user) {
    Optional<MasterUser> userOptional = masterUserRepository.findById(user.getId());
    if(userOptional.isEmpty()) {
      return new ApiResponse<>("User not found");
    }
    MasterUser savedUser = userOptional.get();
    savedUser.setFullName(user.getFullName() != null ? user.getFullName() : savedUser.getFullName() );
    savedUser.setEmail(user.getEmail() != null ? user.getEmail() : savedUser.getEmail() );
    masterUserRepository.save(savedUser);
    return new ApiResponse<>(savedUser);
  }
}
