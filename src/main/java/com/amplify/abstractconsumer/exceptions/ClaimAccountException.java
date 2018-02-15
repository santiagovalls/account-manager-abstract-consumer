package com.amplify.abstractconsumer.exceptions;

import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.ClaimType;

/**
 * @author Santiago J. Valls.
 */
public class ClaimAccountException extends Exception {

  private Account account;

  private ClaimType claimType;

  public ClaimAccountException(Account account, ClaimType claimType) {
    this.account = account;
    this.claimType = claimType;
  }

  public Account getAccount() {
    return account;
  }

  public ClaimAccountException setAccount(Account account) {
    this.account = account;
    return this;
  }

  public ClaimType getClaimType() {
    return claimType;
  }

  public ClaimAccountException setClaimType(ClaimType claimType) {
    this.claimType = claimType;
    return this;
  }
}
