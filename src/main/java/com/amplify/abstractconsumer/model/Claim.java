package com.amplify.abstractconsumer.model;

/**
 * @author Santiago J. Valls.
 */
public class Claim {

  private Account account;

  private ClaimType claimType;

  public Claim(Account account, ClaimType claimType) {
    this.account = account;
    this.claimType = claimType;
  }

  public Account getAccount() {
    return account;
  }

  public Claim setAccount(Account account) {
    this.account = account;
    return this;
  }

  public ClaimType getClaimType() {
    return claimType;
  }

  public Claim setClaimType(ClaimType claimType) {
    this.claimType = claimType;
    return this;
  }
}
