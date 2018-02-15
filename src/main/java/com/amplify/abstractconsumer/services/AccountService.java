package com.amplify.abstractconsumer.services;

import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.ClaimType;
import com.amplify.abstractconsumer.model.SocialNetwork;

/**
 * @author Santiago J. Valls.
 */
public interface AccountService {

  public Account getNextValidAccount(SocialNetwork socialNetwork);

  public void claimAccount(Account account, ClaimType claimType);
}
