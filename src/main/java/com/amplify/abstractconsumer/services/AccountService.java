package com.amplify.abstractconsumer.services;

import com.amplify.abstractconsumer.model.Account;
import com.amplify.abstractconsumer.model.ClaimType;
import com.amplify.abstractconsumer.model.SocialNetwork;
import java.util.Map;

/**
 * @author Santiago J. Valls.
 */
public interface AccountService {

  Map<SocialNetwork, Account> getNextValidAccounts();

  void claimAccount(Account account, ClaimType claimType);
}
