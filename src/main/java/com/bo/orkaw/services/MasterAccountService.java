package com.bo.orkaw.services;

import com.bo.orkaw.models.ApiResponse;
import com.bo.orkaw.models.MasterUser;
import com.bo.orkaw.models.accounts.MasterAccount;
import com.bo.orkaw.models.accounts.MasterAccountReqModel;
import com.bo.orkaw.models.accounts.TotalBalanceModel;
import com.bo.orkaw.repositories.MasterAccountRepository;
import com.bo.orkaw.repositories.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MasterAccountService {

  @Autowired
  private MasterAccountRepository masterAccountRepository;

  @Autowired
  private MasterUserService masterUserService;
  @Autowired
  private MasterUserRepository masterUserRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<MasterAccount> getAllAccounts() {
    return masterAccountRepository.findAll();
  };

  public ApiResponse<Object> getTotalBalanceUser(UUID id) {
    Optional<MasterUser> optionalUser = masterUserService.getDetailUser(id);
    if(optionalUser.isEmpty()) {
      return new ApiResponse<>("User not found");
    }

    MasterUser user = optionalUser.get();
    Double totalBalance = masterAccountRepository.getTotalBalanceByUserId(id);
    TotalBalanceModel totalBalanceModel = new TotalBalanceModel();
    totalBalanceModel.setUserId(user.getId());
    totalBalanceModel.setFullName(user.getFullName());
    totalBalanceModel.setBalance(totalBalance);

    return new ApiResponse<>(totalBalanceModel);
  }

  public ApiResponse<Object> addAccount(MasterAccountReqModel accountPayload) {
    Optional<MasterUser> optionalUser = masterUserService.getDetailUser(accountPayload.getUserId());
    if(optionalUser.isEmpty()) {
      return new ApiResponse<>("User not found");
    }

    MasterUser user = optionalUser.get();
    MasterAccount account = new MasterAccount();
    account.setUser(user);
    account.setBalance(accountPayload.getBalance());

    MasterAccount savedAccount = masterAccountRepository.save(account);
    return new ApiResponse<>(savedAccount);
  }

  public ApiResponse<Object> getTotalBalanceUserWithJdbc(UUID id) {
    byte[] uuidBytes = uuidToBytes(id);
    Optional<MasterUser> optionalUser = masterUserService.getDetailUser(id);
    if(optionalUser.isEmpty()) {
      return new ApiResponse<>("User not found");
    }

    String sql = "SELECT COALESCE(SUM(balance), 0) from MASTER_ACCOUNT_L WHERE user_id = ?";
    double balance = jdbcTemplate.queryForObject(sql, new Object[]{uuidBytes}, Double.class);

    MasterUser user = optionalUser.get();
    TotalBalanceModel totalBalanceModel = new TotalBalanceModel();
    totalBalanceModel.setUserId(user.getId());
    totalBalanceModel.setFullName(user.getFullName());
    totalBalanceModel.setBalance(balance);

    return new ApiResponse<>(totalBalanceModel);
  }

  private byte[] uuidToBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }
}
